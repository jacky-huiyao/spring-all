package com.hy.mybatis.service.impl;

import com.hy.mybatis.dao.UserDao;
import com.hy.mybatis.domain.User;
import com.hy.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }
}
