package com.atguigu.springmvc.interceptor;

import com.atguigu.springmvc.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

/**
 * 登录认证拦截器。不登录，资源不让访问。
 */
@Component //拦截器组件
public class LoginProtectInterceptor implements HandlerInterceptor {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        String username = (String)session.getAttribute("username");
        if(!StringUtils.hasText(username)){ //未登录

            //给浏览器返回结果
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            Result result = Result.fail(501,"未登录，无权访问,请先登录");

            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(result)); //将Result对象序列化为json串返回给客户端浏览器
            writer.flush();
            writer.close();

            return false; //不放行
        }

        return true; //放行
    }
}
