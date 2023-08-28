package com.example.springcrud.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderListSearchCondForm {
   
    private String orderId;

    private String orderer;

    private String productName;

    private int orderQuantity;
 
}
