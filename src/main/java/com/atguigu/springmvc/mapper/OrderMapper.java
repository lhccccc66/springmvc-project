package com.atguigu.springmvc.mapper;

import com.atguigu.springmvc.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    Order selectOrderWithCustomer(@Param("orderId") Integer orderId);
}
