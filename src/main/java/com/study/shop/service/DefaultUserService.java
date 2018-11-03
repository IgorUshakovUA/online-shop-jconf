package com.study.shop.service;

import com.study.shop.dao.UserDao;
import com.study.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String userName, String password) {
        return userDao.getUser(userName, password);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
