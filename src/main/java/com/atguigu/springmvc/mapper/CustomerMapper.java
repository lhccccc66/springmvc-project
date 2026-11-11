package com.atguigu.springmvc.mapper;

import com.atguigu.springmvc.entities.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerMapper {
    Customer selectCustomerWithOrderList(@Param("customerId") Integer customerId);
}
