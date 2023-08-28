package com.example.springcrud.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderEntity extends ManagementItemEntity {
    private String orderId;

    private String orderer;

    private String productName;

    private int orderQuantity;

    private String orderDate;

    private String adress;

    private String tel;
}
