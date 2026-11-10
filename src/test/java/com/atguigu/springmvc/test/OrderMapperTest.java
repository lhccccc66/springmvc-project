package com.atguigu.springmvc.test;

import com.atguigu.springmvc.entities.Order;
import com.atguigu.springmvc.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderMapperTest {
    @Autowired
    OrderMapper orderMapper;

    @Test
    public void test() {
        Order order = orderMapper.selectOrderWithCustomer(1);
        System.out.println(order);
    }
}
