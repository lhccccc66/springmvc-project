package com.atguigu.springmvc.dao;

import com.atguigu.springmvc.util.SysUserDataUtil;
import org.springframework.stereotype.Repository;

@Repository //持久化层组件
public class LoginDaoImpl implements LoginDao{
    @Override
    public boolean login(String username, String password) {
        return SysUserDataUtil.login(username, password);
    }
}
