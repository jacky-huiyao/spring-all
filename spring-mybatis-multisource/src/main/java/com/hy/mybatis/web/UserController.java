package com.hy.mybatis.web;

import com.hy.mybatis.domain.User;
import com.hy.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public @ResponseBody  List<User> list(){
        return userService.findAll();
    }

    @PostMapping
    public void saveUser(@RequestBody User user){
        userService.save(user);
    }
}
