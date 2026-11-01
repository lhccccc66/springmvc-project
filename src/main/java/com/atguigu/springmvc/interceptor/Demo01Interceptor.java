package com.atguigu.springmvc.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//拦截器开发测试
@Component //拦截器组件
public class Demo01Interceptor implements HandlerInterceptor {

    //请求目标前拦截
    // true : 放行直接访问目标
    // false: 拒绝访问目标
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Demo01Interceptor - preHandle ...");
        return true;
    }

    //请求目标后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Demo01Interceptor - postHandle ...");
    }

    //请求目标返回并执行视图解析(转发视图)后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Demo01Interceptor - afterCompletion ...");
    }
}
