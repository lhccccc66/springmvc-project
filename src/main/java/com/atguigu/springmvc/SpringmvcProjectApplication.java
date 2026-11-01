package com.atguigu.springmvc;

import com.atguigu.springmvc.controller.LoginController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(value = {"com.atguigu.spring.user","com.atguigu.spring.order"}) //自定义设置扫描包位置  不推荐。
public class SpringmvcProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(SpringmvcProjectApplication.class, args);

//        LoginController loginController = (LoginController)ioc.getBean("loginController11111"); //根据组件对象id获取
//
//        LoginController loginController2 = (LoginController)ioc.getBean(LoginController.class); //根据组件类型获取
//
//        LoginController loginController3 = (LoginController)ioc.getBean("loginController",LoginController.class); //使用名称+类型获取
//
//        System.out.println("loginController = " + loginController);
//        System.out.println("loginController2 = " + loginController2);
//        System.out.println("loginController3 = " + loginController3);
    }

}
