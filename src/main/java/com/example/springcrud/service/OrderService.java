package com.example.springcrud.service;

import java.util.List;

import com.example.springcrud.dto.OrderDto;
import com.example.springcrud.entity.OrderEntity;
import com.example.springcrud.form.OrderForm;
import com.example.springcrud.form.OrderListSearchCondForm;

public interface OrderService {

    //    @Override
    //    public SampleOrderDto findById(String orderId) {
    //        return convertEntity2Dto(dao.selectById(orderId));
    //    }
    OrderEntity findById(String orderId);

    //    @Override
    List<OrderDto> findByCond(OrderListSearchCondForm form);

    //    @Override
    int logicalRemove(String orderId);

    //    @Override
    int pysicalRemove(String userId);

    //    @Override
    int update(OrderDto dto);

    int update(OrderForm form);

    //    @Override
    int create(OrderForm form);

    void transactionTest(String userId) throws Exception;
}