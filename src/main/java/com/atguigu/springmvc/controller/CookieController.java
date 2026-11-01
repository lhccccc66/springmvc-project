package com.atguigu.springmvc.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookieController {


    @GetMapping("/setCookieData")
    public String setCookieData(HttpServletResponse response, HttpServletRequest request){
        //利用response响应cookie数据给客户端，客户端存储cookie数据。

        Cookie cookie = new Cookie("cookieTip", "aaa.bbb.ccc");
        cookie.setMaxAge(3600); //秒  有效期
        cookie.setPath("/"); //获取cookie的路径。表示任何请求路径都可以获取cookie
        response.addCookie(cookie);

        return "redirect:/showCookieData";
    }


    @GetMapping("/showCookieData")
    public String showCookieData(HttpServletResponse response, HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("cookieTip".equalsIgnoreCase(cookie.getName())){
                request.setAttribute("cookieTip", cookie.getValue());
                break;
            }
        }


        return "cookie-page";
    }

}
