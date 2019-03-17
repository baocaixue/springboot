package com.isaac.springboot.springboot_in_action.dao;

import com.isaac.springboot.springboot_in_action.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Integer updateUserInfo(User user) {
        return jdbc.update("update t_user set name = ? WHERE  id = ?", user.getName(), user.getId());
    }

    @Override
    public List<User> queryUsersByPage(int limitStart, int limitEnd) {
        String sql = "select * from t_user limit ?,?";
        List<User> list = jdbc.query(sql, new User.UserRowMapper(), limitStart, limitEnd);
        return list;
    }
}
