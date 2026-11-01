package com.atguigu.springmvc.controller;

import com.atguigu.springmvc.entities.SysUser;
import com.atguigu.springmvc.service.SysUserService;
import com.atguigu.springmvc.service.SysUserServiceImpl;
import com.atguigu.springmvc.util.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 第二期接口：  需要通过Result作为统一返回结果
 */
@RestController  //组合：  @Controller + @ResponseBody
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService ;

    //需求1： 用户列表条件查询接口
    @GetMapping("list") //只支持get请求
    public Result findSysUserList(@RequestParam(value = "username", required = false, defaultValue = "") String username,
                                  @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                                  @RequestParam(value = "status", required = false, defaultValue = "") Integer status) {

        List<SysUser> sysUserList = sysUserService.findUserList(username, phone, status);

        if (!CollectionUtils.isEmpty(sysUserList)) {
            return Result.success(sysUserList);
        } else {
            return Result.fail("数据为空");
        }

    }

    //需求2： 获取用户详情信息接口
    @GetMapping("{id}")
    public Result getSysUserById(@PathVariable Long id) {

        SysUser sysUser = sysUserService.getById(id);
        if (sysUser != null) {
            return Result.success(sysUser);
        } else {
            return Result.fail("数据为空");
        }
    }

    // BindingResult 用于封装校验错误信息
    @PostMapping
    public Result saveUser(@Valid @RequestBody SysUser sysUser, BindingResult bindingResult) {

        boolean hasErrors = bindingResult.hasErrors();
        if (hasErrors) {
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            String errorMessage = fieldErrorList.stream().map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage())
                    .collect(Collectors.joining(";"));
            return Result.fail(501, errorMessage);
        }

        sysUser.setPassword("1234556"); //默认密码
        SysUser sysUserDB = sysUserService.save(sysUser);
        if (sysUserDB != null && sysUserDB.getId() != null) {
            return Result.success(sysUserDB);
        } else {
            return Result.fail("添加失败");
        }
    }


    //案例2 - 2 修改用户
    @PutMapping("/{id}")
    public Result updateUser(@PathVariable Long id, @RequestBody SysUser sysUser) {
        boolean isOk = sysUserService.updateById(id, sysUser);
        if (isOk) {
            SysUser sysUserDB = sysUserService.getById(id);
            sysUserDB.setPassword(null); //擦除密码，保证安全
            return Result.success(sysUserDB);
        }
        return Result.fail("修改失败");
    }

    //案例3 - 3 删除用户
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable Long id) {
        boolean isOk = sysUserService.deleteById(id);
        if (isOk) {
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

}

