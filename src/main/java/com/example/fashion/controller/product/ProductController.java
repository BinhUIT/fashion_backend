package com.example.fashion.controller.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fashion.domain.entity.mysqltables.Product;
import com.example.fashion.domain.selectcolumninterface.ProductInfo;
import com.example.fashion.dto.response.GlobalResponse;
import com.example.fashion.service.productservice.ProductFilterService;
import com.example.fashion.service.productservice.ProductService;
import com.example.fashion.util.Message;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductFilterService productFilterService;
    public ProductController(ProductService productService, ProductFilterService productFilterService) {
        this.productService= productService;
        this.productFilterService= productFilterService;
    } 
    @GetMapping("/get_products")
    public ResponseEntity<GlobalResponse<Page<ProductInfo>>> getProducts(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="0") int size) {
        Page<ProductInfo> result = productService.getProducts(page, size);
        GlobalResponse<Page<ProductInfo>> response = new GlobalResponse<>(Message.successMessage,result,200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<GlobalResponse<Product>> getOneProduct(@PathVariable int id) {
        Product result = productService.getProductDetail(id);
        GlobalResponse<Product> response = new GlobalResponse<>(Message.successMessage,result,200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/filter")
    public ResponseEntity<GlobalResponse<Page<ProductInfo>>> getAllEnableProductByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size,
    @RequestParam(defaultValue = "id") String sortCriteria, @RequestParam(defaultValue = "asc") String order, @RequestParam(required = false) List<Long> catIds, @RequestParam(defaultValue="") String catName,
    @RequestParam(defaultValue="-1") int fromPrice, @RequestParam(defaultValue="-1") int toPrice, @RequestParam(required = false) List<Integer> sizeIds,  @RequestParam(required = false) List<Integer> colorIds,
    @RequestParam(defaultValue="") String keyword) { 
       
        Page<ProductInfo> result=productFilterService.filterProduct(page, size, sortCriteria, order, catIds, catName, fromPrice, toPrice, sizeIds, colorIds, keyword);
        GlobalResponse<Page<ProductInfo>> response = new GlobalResponse<>(Message.successMessage,result,200);
        return new ResponseEntity<>(response, HttpStatus.OK);
       
        
    }
}
