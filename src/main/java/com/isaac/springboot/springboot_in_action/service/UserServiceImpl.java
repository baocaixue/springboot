package com.isaac.springboot.springboot_in_action.service;

import com.isaac.springboot.springboot_in_action.dao.UserDao;
import com.isaac.springboot.springboot_in_action.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = false)
    public Integer updateUserInfo(User user) {
        if (user != null) {
            int result = userDao.updateUserInfo(user);
            return result;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> queryUsersByPage(int page, int size) {
        int limitStart = (page - 1) * size;
        int limitEnd = limitStart + size;
        return userDao.queryUsersByPage(limitStart,limitEnd);
    }
}
