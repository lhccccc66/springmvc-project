package com.atguigu.springmvc.entities;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

/**
 * 系统用户实体类（对应sys_user表）
 */
@Data // 自动生成get/set/toString/equals/hashCode等方法，需引入lombok依赖
public class SysUser {
    private Long id;         // 用户唯一ID

    // 保存必填，更新可选：用户名（非空+长度2-20）
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度需在2-20位之间")
    private String username; // 用户名
    private String password; // 密码（BCrypt加密存储）

    // 保存必填，更新可选：手机号（非空+11位合法格式）
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误（需为11位合法手机号）")
    private String phone;    // 手机号（唯一）
    private String avatar;   // 用户头像URL

    // 保存默认1，更新可选：用户状态（仅允许0-禁用/1-正常）
    @Min(value = 0, message = "用户状态仅支持0（禁用）或1（正常）")
    @Max(value = 1, message = "用户状态仅支持0（禁用）或1（正常）")
    private Integer status;  // 账号状态：0-禁用，1-正常

    // 保存必填，更新可选：角色ID
    @NotNull(message = "角色ID不能为空")
    private Long roleId;     // 角色ID（关联角色表）
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间
}
