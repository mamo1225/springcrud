package com.example.springcrud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.springcrud.entity.OrderEntity;

@Mapper
public interface OrderMapper {
    int insert(OrderEntity entity);

    int update(OrderEntity entity);

    int deleteLogical(OrderEntity entity);

    int delete(OrderEntity entity);

    OrderEntity selectById(String orderId);

    List<OrderEntity> selectByCond(String orderId, String orderer, String productName, int orderQuantity);

}
