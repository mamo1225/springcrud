package com.example.springcrud.form;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {

    @NotEmpty(message = "{XXXDM001}")
    private String orderId;

    private String orderer;

    private String productName;

    private int orderQuantity;

    private String orderDate;

    private String adress;

    private String tel;

    private Integer version;
}
