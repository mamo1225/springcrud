package com.example.springcrud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springcrud.dto.OrderDto;
import com.example.springcrud.entity.OrderEntity;
import com.example.springcrud.form.OrderForm;
import com.example.springcrud.form.OrderListSearchCondForm;
import com.example.springcrud.mapper.OrderMapper;

import lombok.var;

@Transactional(rollbackFor = Throwable.class) // アノテーションを付加したクラスのメソッド全てに対し、例外がスローされたらロールバックする。 メソッドに対して付加するとそちらが優先される
@Service // サービス層のクラスではかならず定義する必要あり
//@AllArgsConstructor // プロパティの値を全てもったコンストラクタを定義
public class OrderServiceImpl implements OrderService {
    //    public class SampleOrderServiceImpl implements SampleOrderService {

	@Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderEntity findById(String orderId) {
        return orderMapper.selectById(orderId);
    }

    @Override
    public List<OrderDto> findByCond(OrderListSearchCondForm form) {
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(form, dto);
//        return orderMapper.selectByCond(dto).stream().map(this::convertEntity2Dto).collect(Collectors.toList());
        List<OrderEntity> orderList = orderMapper.selectByCond(StringUtils.stripToNull(dto.getOrderId()), StringUtils.stripToNull(dto.getOrderer()), StringUtils.stripToNull(dto.getProductName()), dto.getOrderQuantity());
        return orderList.stream().map(this::convertEntity2Dto).collect(Collectors.toList());

    }

    @Override
    public int logicalRemove(String orderId) {
        OrderEntity entity = orderMapper.selectById(orderId);
        if (entity != null) {
            return orderMapper.deleteLogical(entity);
        }
        return 0;
    }

    @Override
    public int pysicalRemove(String userId) {
        OrderEntity entity = orderMapper.selectById(userId);
        return orderMapper.delete(entity);
    }

    @Override
    public int update(OrderDto dto) {
        //        var result = dao.selectById(dto.getUserId());
        //        if (result.isPresent()) {
        //            var entity = result.get();
        //            //            entity.setUserName(dto.getUserName());
        //            return dao.update(entity);
        //        }
        return 0;
    }

    @Override
    public int update(OrderForm form) {
        OrderEntity entity = orderMapper.selectById(form.getOrderId());
        if (entity != null) {
            entity.setOrderer(form.getOrderer());
            entity.setProductName(form.getProductName());
            entity.setOrderQuantity(form.getOrderQuantity());
            entity.setOrderDate(form.getOrderDate());
            entity.setAdress(form.getAdress());
            entity.setTel(form.getTel());
            entity.setVersion(form.getVersion());
            return orderMapper.update(entity);
        }
        return 0;
    }

    @Override
    public int create(OrderForm form) {
        OrderEntity entity = new OrderEntity();
        BeanUtils.copyProperties(form, entity);
        return orderMapper.insert(entity);
    }

    private OrderDto convertEntity2Dto(OrderEntity entity) {
        if (entity == null) {
            return null;
        }
        var dto = new OrderDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    private OrderEntity createEntityFromDto(OrderDto dto) {
        OrderEntity entity = new OrderEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public void transactionTest(String userId) throws Exception {
        //何もしない
    }
}
