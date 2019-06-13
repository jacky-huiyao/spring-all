package com.hy.mybatis.service.impl;

import com.hy.mybatis.annotation.DataSource;
import com.hy.mybatis.dao.master.UserDao;
import com.hy.mybatis.dao.slave.UserQueryDao;
import com.hy.mybatis.domain.User;
import com.hy.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserQueryDao userQueryDao;

    @Override
    public User findById(Integer id) {
        return userQueryDao.findById(id);
    }

    @Override
    @DataSource("slave")
    public List<User> findAll() {
        return userQueryDao.findAll();
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}
