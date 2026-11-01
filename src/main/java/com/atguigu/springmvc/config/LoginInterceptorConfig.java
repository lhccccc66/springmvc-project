package com.atguigu.springmvc.config;

import com.atguigu.springmvc.interceptor.LoginProtectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    LoginProtectInterceptor loginProtectInterceptor ;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginProtectInterceptor)
                .addPathPatterns("/user/**")  //设置拦截目标
                .excludePathPatterns("/auth/login","/auth/logout","*.html","*.css","*.js");  //排除拦截目标
    }

/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginProtectInterceptor())
                .addPathPatterns("/user/**")  //设置拦截目标
                .excludePathPatterns("/auth/login","/auth/logout","*.html","*.css","*.js");  //排除拦截目标
    }*/
}
