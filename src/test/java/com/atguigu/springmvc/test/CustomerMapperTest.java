package com.atguigu.springmvc.test;

import com.atguigu.springmvc.entities.Customer;
import com.atguigu.springmvc.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerMapperTest {

    @Autowired
    CustomerMapper customerMapper;

    @Test
    public void testSelectCustomerWithOrderList() {
        Customer customer = customerMapper.selectCustomerWithOrderList(1);
        System.out.println("customer = " + customer);
        System.out.println("订单数量 = " + customer.getOrderList().size());
    }
}
