package com.isaac.springboot.springboot_in_action.controller;

import com.isaac.springboot.springboot_in_action.entity.User;
import com.isaac.springboot.springboot_in_action.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("updateUser")
    public Map<String,Boolean> updateUserInfo(@RequestBody User user){
        Map<String,Boolean> resultMap = new HashMap<>();
        Integer i = userService.updateUserInfo(user);
        resultMap.put("execute status",i != null && i > 0);
        return resultMap;
    }

    @RequestMapping("queryUsers")
    public List<User> queryUsersLimit(){
        List<User> users = userService.queryUsersByPage(1,10);
        return users;
    }
}
