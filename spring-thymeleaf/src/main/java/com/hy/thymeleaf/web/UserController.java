package com.hy.thymeleaf.web;

import com.hy.thymeleaf.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @RequestMapping("")
    public String hello(ModelMap map) {
        map.addAttribute("message", "http://www.ityouknow.com");
        return "hello";
    }

    @RequestMapping("for")
    public String testFor(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "for";
    }

    private List<User> getUserList(){
        List<User> list= new ArrayList<>();
        User user1=new User("你",14);
        User user2=new User("我",null);
        User user3=new User("他",24);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return  list;
    }
}
