package com.atguigu.springmvc.controller;

import com.atguigu.springmvc.dto.SysUserLoginDto;
import com.atguigu.springmvc.service.LoginService;
import com.atguigu.springmvc.service.LoginServiceImpl;
import com.atguigu.springmvc.util.Result;
import com.atguigu.springmvc.util.SysUserDataUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * 登录和注销
 */
@RestController
@RequestMapping("/auth")
//@Scope(value="singleton")
//@Scope(value="prototype")
public class LoginController {

    @Autowired
    LoginService loginService ;

    //登录
    @PostMapping("login")
    public Result login(@Valid @RequestBody SysUserLoginDto sysUserLoginDto,
                        BindingResult bindingResult,
                        HttpSession session) {


        //1.数据校验？
        if (bindingResult.hasErrors()) {
            return Result.fail(500,"您的账号或密码格式错误");
        }

        //2.没用数据库是否有账号密码
        boolean loginOK = loginService.login(sysUserLoginDto.getUsername(),sysUserLoginDto.getPassword());
        if(!loginOK){
            return Result.fail(500,"您的账号或密码错误");
        }


        //3.登录成功
        session.setAttribute("username", sysUserLoginDto.getUsername()); //登录成功后跳转成功页面，成功页面显示欢迎信息。
        return Result.success("登录成功");
    }

    //注销
    @GetMapping("logout")
    public Result logout(HttpSession session) {
        session.removeAttribute("username");
        session.invalidate(); //销毁session
        return Result.success("注销成功");
    }
}
