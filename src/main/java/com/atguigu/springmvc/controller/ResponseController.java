package com.atguigu.springmvc.controller;

import com.atguigu.springmvc.util.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//重点：HttpServletResponse使用
@Controller
public class ResponseController {

    @RequestMapping("writeText")
    public void writeText(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=utf-8");
        response.setHeader("token","aaa.bbb.ccc");
        response.getWriter().write("服务器给客户端返回一个字符串...");
    }



}
