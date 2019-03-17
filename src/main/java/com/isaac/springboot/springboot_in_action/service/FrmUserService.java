package com.isaac.springboot.springboot_in_action.service;

import com.isaac.springboot.springboot_in_action.entity.FrmUser;

import java.util.List;

public interface FrmUserService {
    Integer addFrmUser(FrmUser user);

    List<FrmUser> querySortedUsers();

    List<FrmUser> queryPagedUsers(int page, int size);
}
