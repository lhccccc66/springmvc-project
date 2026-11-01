package com.atguigu.springmvc.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 注意：混合开发需用@Controller（而非@RestController），返回值为页面路径而非JSON
@Controller
@RequestMapping("/jump")
public class JumpController {
    /**
     * 转发入口方法：触发服务器内部跳转
     * 语法：return "forward:目标请求路径"
     */
    @GetMapping("/toForward")
    public String toForward(HttpServletRequest request, HttpSession session) {
        // 转发到/jump/showForward路径（服务器内部跳转，浏览器无感知）

        request.setAttribute("a1","a11"); //同一次请求有效
        session.setAttribute("b1","b11"); //同一个客户端(同一个会话)，多次请求有效

        ServletContext application = request.getServletContext(); //不同客户端，不同请求都有效
        application.setAttribute("c1","c11");

        return "forward:/jump/showForward";
    }

    /**
     * 转发目标方法：映射到具体页面
     * 返回值"forward-page"对应resources/templates/forward-page.html
     */
    @GetMapping("/showForward")
    public String showForward(HttpServletRequest request, HttpSession session) {
        // SpringBoot中Thymeleaf页面默认存放在resources/templates目录下

        String a1 = (String)request.getAttribute("a1"); //从请求域获取数据
        System.out.println("a1 = " + a1);

        String b1 = (String)session.getAttribute("b1"); //从session域获取数据
        System.out.println("b1 = " + b1);

        ServletContext application = request.getServletContext();
        String c1 = (String)application.getAttribute("c1");
        System.out.println("c1 = " + c1);

        return "forward-page";
    }





    /**
     * 重定向入口方法：通知浏览器发起二次请求
     * 语法：return "redirect:目标请求路径"
     */
    @GetMapping("/toRedirect")
    public String toRedirect(HttpServletRequest request, HttpSession session) {
        // 通知浏览器重定向到/jump/showRedirect路径


        request.setAttribute("a1","a11"); //同一次请求有效
        session.setAttribute("b1","b11"); //同一个客户端(同一个会话)，多次请求有效

        ServletContext application = request.getServletContext(); //不同客户端，不同请求都有效
        application.setAttribute("c1","c11");

        return "redirect:/jump/showRedirect";
        //return "redirect:http://www.atguigu.com";
    }

    /**
     * 重定向目标方法：映射到具体页面
     * 返回值"redirect-page"对应resources/templates/redirect-page.html
     */
    @GetMapping("/showRedirect")
    public String showRedirect(HttpServletRequest request, HttpSession session) {


        String a1 = (String)request.getAttribute("a1"); //从请求域获取数据
        System.out.println("a1 = " + a1);

        String b1 = (String)session.getAttribute("b1"); //从session域获取数据
        System.out.println("b1 = " + b1);

        ServletContext application = request.getServletContext();
        String c1 = (String)application.getAttribute("c1");
        System.out.println("c1 = " + c1);

        return "redirect-page";
    }




}
