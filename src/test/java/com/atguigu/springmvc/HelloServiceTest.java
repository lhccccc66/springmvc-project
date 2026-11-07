package com.atguigu.springmvc;

import com.atguigu.springmvc.service.HelloService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

//@SpringBootTest //声明一个测试类
// webEnvironment = RANDOM_PORT：启动随机端口，避免端口冲突
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloServiceTest {


    @Autowired
    HelloService helloService;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test //声明一个测试方法
    public void testSayHello() {
        String result = helloService.sayHello("boot");
        System.out.println(result);
        Assertions.assertThat(result).isEqualTo("Hello, Boot!");
    }

    @Test //声明一个测试方法
    public void testWebSayHello() {
        String result = testRestTemplate.getForObject("/getValue",String.class);
        Assertions.assertThat(result).isEqualTo("ok!!!!");
    }

}
