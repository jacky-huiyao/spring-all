package com.hy.mybatis.dao.slave;

import com.hy.mybatis.domain.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserQueryDao {

    @Results(id = "userMap",value = {
        @Result(column = "birth_date",property = "birthDate",javaType = java.time.LocalDate.class),
        @Result(column = "last_login_time",property = "lastLoginTime",javaType = java.time.LocalDateTime.class)
    })
    @Select("select * from templates.article  where id = #{id}")
    User findById(Integer id);


    @ResultMap(value = "userMap")
    @Select("select * from templates.article")
    List<User> findAll();
}
