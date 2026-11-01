package com.atguigu.springmvc.dao;

import com.atguigu.springmvc.entities.SysUser;

import java.util.List;

/**
 * 持久化层接口：用户模块
 */
public interface SysUserDao {
    boolean deleteById(Long id);

    SysUser getById(Long id);

    boolean updateById(Long id, SysUser sysUser);

    SysUser save(SysUser sysUser);

    List<SysUser> findUserList(String username, String phone, Integer status);
}
