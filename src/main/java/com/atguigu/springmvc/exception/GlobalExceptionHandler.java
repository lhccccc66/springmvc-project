package com.atguigu.springmvc.exception;

import com.atguigu.springmvc.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice //声明异常统一处理类
public class GlobalExceptionHandler {

    //专业处理空指针异常
    @ExceptionHandler(NullPointerException.class)
    public Result handlerNullPointerException(NullPointerException e){
        e.printStackTrace();
        return Result.fail("空指针异常");
    }

    //专业处理流异常
    @ExceptionHandler(IOException.class)
    public Result handlerIOException(IOException e){
        e.printStackTrace();
        return Result.fail("IO流异常");
    }

    //处理额外其他异常，统一处理
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        e.printStackTrace();
        return Result.fail("系统异常");
    }
}
