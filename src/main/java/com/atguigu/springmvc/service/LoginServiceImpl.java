package com.atguigu.springmvc.service;

import com.atguigu.springmvc.dao.LoginDao;
import com.atguigu.springmvc.dao.LoginDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service //业务层组件
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao ;

    @Override
    public boolean login(String username, String password) {
        return loginDao.login(username, password);
    }
}
