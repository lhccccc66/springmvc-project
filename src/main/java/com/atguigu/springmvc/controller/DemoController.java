package com.atguigu.springmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//请求方法和请求路径不同同时一致。否则声明上会出现冲突。
@RestController
public class DemoController {
    // 方法1：映射 GET /emp
    @GetMapping("/emp")
    public String empGet1() {
        return "empGet1";
    }

    // 方法2：同样映射 GET /emp → 触发冲突
//    @GetMapping("/emp")
//    public String empGet2() {
//        return "empGet2";
//    }
}
