package com.atguigu.springmvc;

import com.atguigu.springmvc.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SpringmvcProjectApplicationTests {

    @Autowired
    HelloService helloService;

    @Test
    public void testHelloService(){
        String Result = helloService.sayHello("boot");
        System.out.println(Result);
        assertThat(Result).isEqualTo("Hello, boot!");

    }

}
