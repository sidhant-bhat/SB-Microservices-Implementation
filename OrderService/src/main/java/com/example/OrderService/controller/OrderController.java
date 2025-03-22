package com.example.OrderService.controller;

import com.example.OrderService.exception.CustomException;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.yaml.snakeyaml.nodes.Tag.STR;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<List<Long>>placeOrder(@RequestBody OrderRequest orderRequest) {
        long productId = orderRequest.getProductId();
      long orderId = orderService.placeOrder(orderRequest);
      log.info("Order Id:{}",orderId);
      //return new  ResponseEntity<>(orderId, HttpStatus.OK)
            List<Long> response = Arrays.asList(orderId, productId);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
