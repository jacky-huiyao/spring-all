package com.hy.mybatis.dao.master;


import com.hy.mybatis.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface UserDao {

    @Insert("insert into templates.article(name,birth_date,salary,status,last_login_time)  " +
            "values(#{name},#{birthDate},#{salary},#{status},#{lastLoginTime})")
    void save(User user);


}
