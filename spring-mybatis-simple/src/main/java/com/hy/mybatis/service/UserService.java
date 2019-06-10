package com.hy.mybatis.service;

import com.hy.mybatis.domain.User;

import java.util.List;

/**
 *
 */
public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    void save(User user);

    void update(User user);

}
