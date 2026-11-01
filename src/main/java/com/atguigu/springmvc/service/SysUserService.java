package com.atguigu.springmvc.service;

import com.atguigu.springmvc.entities.SysUser;

import java.util.List;

/**
 * 业务层接口：用户模块
 */
public interface SysUserService {
    boolean deleteById(Long id);

    SysUser getById(Long id);

    boolean updateById(Long id, SysUser sysUser);

    SysUser save(SysUser sysUser);

    List<SysUser> findUserList(String username, String phone, Integer status);
}
