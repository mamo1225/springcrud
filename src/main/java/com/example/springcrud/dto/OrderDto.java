package com.example.springcrud.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class OrderDto {
    private String orderId;
    private String orderer;
    private String productName;
    private int orderQuantity;
    private String orderDate;
    private String adress;
    private String tel;
    private Integer version;
}
