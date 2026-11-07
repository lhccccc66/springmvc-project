package com.atguigu.springmvc.controller;

import com.atguigu.springmvc.bean.DbBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 加载外部文件，指定UTF-8编码避免中文乱码
@PropertySource(encoding = "UTF-8", value = "classpath:config/db.properties")
//@EnableConfigurationProperties(DbBean.class)
public class ValueController {

    @Value("${app.port:9999}") //获取属性文件的自定义属性值，如果属性不存在，那么取默认值
    private Integer port;

    @Value("${db.username}")
    private String username;

    @Value("${db.mysql.driver}")
    private String driverClass;

    @Autowired
    private DbBean dbBean;

    @GetMapping("/getValue")
    public String hello() {
        System.out.println("port = " + port);
        System.out.println("username = " + username);
        System.out.println("driverClass = " + driverClass);
        System.out.println("dbBean = " + dbBean);
        //return "username=" + username + ",port=" + port + ",driverClass=" + driverClass+",dbBean=" + dbBean;
        return "ok";
    }
}
