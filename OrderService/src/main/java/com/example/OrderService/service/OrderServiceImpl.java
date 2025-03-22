package com.example.OrderService.service;



import com.example.OrderService.entity.Orderss;
import com.example.OrderService.external.client.ProductService;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.repository.OrderRepository;


import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public long placeOrder(OrderRequest orderRequest) {


      //Order Entity-> Save the data with Status Order Created
        //Product Service -> Block products/reduce count of product
        //Payment Service -> Payments -> Success -> Complete
        //Cancelled

        log.info("Placing Order Request:{}",orderRequest);
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        log.info("Creating Order with Status CREATED");
        Orderss order = Orderss.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        order = orderRepository.save(order);

        log.info("Order Placed successfully with Order Id:{}",orderRequest);


        return order.getId();
    }
}
