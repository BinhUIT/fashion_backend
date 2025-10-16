package com.example.fashion.service.paymentservice;

import org.springframework.stereotype.Service;

import com.example.fashion.domain.entity.mysqltables.Order;
import com.example.fashion.domain.entity.mysqltables.Payment;
import com.example.fashion.domain.enums.EPaymentType;

import com.example.fashion.repository.PaymentRepository;
import com.example.fashion.util.Message;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepo;
    public PaymentService(PaymentRepository paymentRepo) {
        this.paymentRepo= paymentRepo;
    } 
    public String handlePayment(Order order, EPaymentType paymentType) {
        Payment payment = new Payment(order, "VND", order.getTotal(), "UNPAID", paymentType);
        order.setPayment(payment);
        paymentRepo.save(payment);
        if(paymentType==EPaymentType.COD) {
            return Message.createOrderSuccess;
        } 
        return createPaymentLink(order);
    } 
    private String createPaymentLink(Order order) {
        return "";
    }
}
