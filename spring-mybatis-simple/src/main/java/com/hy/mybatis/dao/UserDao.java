package com.hy.mybatis.dao;


import com.hy.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface UserDao {

    void save(User user);

    User findById(Integer id);

    List<User> findAll();
}
