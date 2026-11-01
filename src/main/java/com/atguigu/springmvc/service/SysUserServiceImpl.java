package com.atguigu.springmvc.service;

import com.atguigu.springmvc.dao.SysUserDao;
import com.atguigu.springmvc.dao.SysUserDaoImpl;
import com.atguigu.springmvc.entities.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //业务层组件
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserDao sysUserDao;

    @Override
    public boolean deleteById(Long id) {
        return sysUserDao.deleteById(id);
    }

    @Override
    public SysUser getById(Long id) {
        return sysUserDao.getById(id);
    }

    @Override
    public boolean updateById(Long id, SysUser sysUser) {
        if (sysUserDao.getById(id) == null) {
            return false;
        }
        return sysUserDao.updateById(id, sysUser);
    }

    @Override
    public SysUser save(SysUser sysUser) {
        return sysUserDao.save(sysUser);
    }

    @Override
    public List<SysUser> findUserList(String username, String phone, Integer status) {
        return sysUserDao.findUserList(username, phone, status);
    }
}
