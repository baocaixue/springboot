package com.isaac.springboot.springboot_in_action.dao;

import com.isaac.springboot.springboot_in_action.entity.User;

import java.util.List;

public interface UserDao {
    Integer updateUserInfo(User user);

    List<User> queryUsersByPage(int limitStart, int limitEnd);
}
