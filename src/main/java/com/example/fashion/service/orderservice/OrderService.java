package com.example.fashion.service.orderservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fashion.domain.entity.mysqltables.Order;
import com.example.fashion.domain.entity.mysqltables.OrderDetail;
import com.example.fashion.domain.entity.mysqltables.ProductVariant;
import com.example.fashion.domain.entity.mysqltables.User;
import com.example.fashion.domain.enums.EOrderStatus;
import com.example.fashion.dto.request.OrderDetailRequest;
import com.example.fashion.dto.request.OrderRequest;
import com.example.fashion.dto.response.OrderCreationResponse;
import com.example.fashion.exception.BadRequestException;
import com.example.fashion.exception.NotFoundException;
import com.example.fashion.repository.OrderRepository;
import com.example.fashion.repository.ProductVariantRepository;
import com.example.fashion.service.authservice.AuthService;
import com.example.fashion.service.paymentservice.PaymentService;
import com.example.fashion.util.Message;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    private static final int SHIPPINGFEE=5;
    private final OrderRepository orderRepo;
    private final AuthService authService;
    private final ProductVariantRepository productVariantRepo;
    private final PaymentService paymentService;
    public OrderService(OrderRepository orderRepo, AuthService authService, ProductVariantRepository productVariantRepo, PaymentService paymentService) {
        this.orderRepo= orderRepo;
        this.authService = authService;
        this.productVariantRepo= productVariantRepo;
        this.paymentService= paymentService;
    }
    @Transactional
    public OrderCreationResponse createOrder(String email, OrderRequest request) {
        Order order = createOrderFromEmailAndRequest(email, request);
        order=orderRepo.save(order);
        List<OrderDetail> listOrderDetails = detailsFromDetailRequestsAndOrder(order, request.getListDetailRequest());
        order.setOrderDetails(listOrderDetails);
        String message = paymentService.handlePayment(order, request.getPaymentType());
        order.setTotal(order.getTotal()+order.getShipping_fee());
        orderRepo.save(order);
        return new OrderCreationResponse(order.getId(), message);
    }
    private Order createOrderFromEmailAndRequest(String email, OrderRequest request) {
        User user = (User) authService.loadUserByUsername(email);
        Order order = new Order();
        order.setUser(user);
        order.setAddress(request.getAddress());
        order.setCreateAt(new Date());
        order.setStatus(EOrderStatus.PENDING);
        order.setShipping_fee(SHIPPINGFEE);
        order.setPhone(request.getPhone());
        order.setOriginPrice(0f);
        return order;
    }
    private List<OrderDetail> detailsFromDetailRequestsAndOrder(Order order, List<OrderDetailRequest> listDetailRequests) {
        List<Integer> listProductVariantId = listDetailRequests.stream().map(OrderDetailRequest::getProductVariantId).toList();
        
        List<ProductVariant> listVariants = productVariantRepo.findByIdIn(listProductVariantId);
        if(listDetailRequests.size()!=listVariants.size()) {
            throw new NotFoundException(Message.oneOrMoreVariantNotFound);
        }
        List<OrderDetail> listOrderDetails = new ArrayList<>();
        for(int i=0;i<listVariants.size();i++) {
            ProductVariant pv = listVariants.get(i);
            OrderDetailRequest detailRequest= listDetailRequests.get(i);
            if(pv.getQuantity()<detailRequest.getQuantity()) {
                throw new BadRequestException(Message.notEnoughProduct);
            }
            OrderDetail orderDetail= new OrderDetail(order, pv, detailRequest.getQuantity());
            order.setOriginPrice(order.getOriginPrice()+pv.getPrice()*detailRequest.getQuantity());
            listOrderDetails.add(orderDetail);
        }
        return listOrderDetails;
    }
    
}
