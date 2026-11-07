package com.atguigu.springmvc.service;

import com.atguigu.springmvc.mapper.SysUserMapper;
import com.atguigu.springmvc.entities.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //业务层组件
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public boolean deleteById(Long id) {
        return sysUserMapper.deleteById(id)>0?true:false;
    }

    @Override
    public SysUser getById(Long id) {
        return sysUserMapper.getById(id);
    }

    @Override
    public boolean updateById(Long id, SysUser sysUser) {
        if (sysUserMapper.getById(id) == null) {
            return false;
        }
        return sysUserMapper.updateById(id, sysUser)>0?true:false;
    }

    @Override
    public SysUser save(SysUser sysUser) {
        return sysUserMapper.save(sysUser);
    }

    @Override
    public List<SysUser> findUserList(String username, String phone, Integer status) {
        return sysUserMapper.findUserList(username, phone, status);
    }
}
