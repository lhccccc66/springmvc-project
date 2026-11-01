package com.atguigu.springmvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO : 数据传输对象
 *
 *  用于接口调用，数据参数封装。
 */
@Data
public class SysUserLoginDto {

    // 保存必填，更新可选：用户名（非空+长度2-20）
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度需在2-20位之间")
    private String username; // 用户名

    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, max = 20, message = "用户密码长度需在6-20位之间")
    private String password; // 密码（BCrypt加密存储）
}
