package com.lh.service.impl;

import com.lh.bean.Users;
import com.lh.dao.UsersDao;
import com.lh.dao.impl.UsersDaoImpl;
import com.lh.service.UsersService;

public class UsersServiceImpl implements UsersService {

    private UsersDao userDao = new UsersDaoImpl();

    @Override
    public Users login(String username, String password) {
        return userDao.login(username, password);
    }
}
