package com.atguigu.springmvc.dao;

import com.atguigu.springmvc.entities.SysUser;
import com.atguigu.springmvc.util.SysUserDataUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 默认持久化层开发。没有真正操作mysql数据库。
 */
@Repository //持久化层组件
public class SysUserDaoImpl implements SysUserDao {
    @Override
    public boolean deleteById(Long id) {
        return SysUserDataUtil.deleteUserById(id);
    }

    @Override
    public SysUser getById(Long id) {
        return SysUserDataUtil.getUserById(id);
    }

    @Override
    public boolean updateById(Long id, SysUser sysUser) {
        return SysUserDataUtil.updateUserById(id, sysUser);
    }

    @Override
    public SysUser save(SysUser sysUser) {
        return SysUserDataUtil.saveUser(sysUser);
    }

    @Override
    public List<SysUser> findUserList(String username, String phone, Integer status) {
        return SysUserDataUtil.listUsers(username, phone, status);
    }
}
