package com.atguigu.springmvc.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//用于封装属性文件的批量属性
@Component //将当前对象纳入IOC容器管理。
@ConfigurationProperties(prefix = "db")
@Data
public class DbBean {
    private String username; //成员变量名称与配置文件中属性名称一致，并且提供get/set方法。
    private String password;
}
