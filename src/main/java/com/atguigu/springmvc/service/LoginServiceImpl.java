package com.atguigu.springmvc.service;


import com.atguigu.springmvc.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //业务层组件
public class LoginServiceImpl implements LoginService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public boolean login(String username, String password) {
        return sysUserMapper.login(username, password);
    }
}
