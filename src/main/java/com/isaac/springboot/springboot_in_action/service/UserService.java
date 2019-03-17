package com.isaac.springboot.springboot_in_action.service;

import com.isaac.springboot.springboot_in_action.entity.User;

import java.util.List;

public interface UserService {
    Integer updateUserInfo(User user);

    List<User> queryUsersByPage(int page, int size);
}
