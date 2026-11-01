package com.atguigu.springmvc.controller;

import com.atguigu.springmvc.util.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response-entity")
public class ResponseEntityController {

    // 示例1：返回自定义状态码+统一Result类（最常用）
    @GetMapping("/user/{id}")
    public ResponseEntity<Result> getUser(@PathVariable Long id) {
        // 模拟查询用户
        if (id == 1) {
            // 成功：200状态码 + Result数据
            Result successResult = Result.success("查询成功", "用户ID：1的信息");
            return new ResponseEntity<>(successResult, HttpStatus.OK);
        } else {
            // 失败：404状态码 + Result提示
            Result failResult = Result.fail(404, "用户不存在");
            return new ResponseEntity<>(failResult, HttpStatus.NOT_FOUND);
        }
    }

    // 示例2：自定义响应头+文本响应
    @GetMapping("/text")
    public ResponseEntity<String> returnText() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "MyCustomValue"); // 自定义响应头
        // 返回：文本内容 + 响应头 + 200状态码
        return new ResponseEntity<>("ResponseEntity返回的文本", headers, HttpStatus.OK);
    }
}
