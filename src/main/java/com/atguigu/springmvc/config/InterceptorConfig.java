package com.atguigu.springmvc.config;

import com.atguigu.springmvc.interceptor.Demo01Interceptor;
import com.atguigu.springmvc.interceptor.Demo02Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //声明一个配置类
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    Demo01Interceptor demo01Interceptor;

    @Autowired
    Demo02Interceptor demo02Interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(demo01Interceptor);
        registry.addInterceptor(demo02Interceptor);

    }

/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new Demo01Interceptor())//注册拦截器
//                .addPathPatterns("/user/**")  //设置拦截目标
//                .excludePathPatterns("/auth/login","/auth/logout","*.html","*.css","*.js");  //排除拦截目标


        registry.addInterceptor(new Demo01Interceptor());
        registry.addInterceptor(new Demo02Interceptor());

    }*/
}
