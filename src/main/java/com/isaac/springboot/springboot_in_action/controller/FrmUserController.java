package com.isaac.springboot.springboot_in_action.controller;

import com.isaac.springboot.springboot_in_action.entity.FrmUser;
import com.isaac.springboot.springboot_in_action.service.FrmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FrmUserController {
    @Autowired
    private FrmUserService frmUserService;

    @PostMapping("saveUser")
    public String saveUser(@RequestBody FrmUser user){
        Integer result = frmUserService.addFrmUser(user);
        return result.toString();
    }

    @PostMapping("querySortedUsers")
    public List<FrmUser> queryUser(){
        return frmUserService.querySortedUsers();
    }

    @PostMapping("queryPagedUsers")
    public List<FrmUser> queryPagedUsers(int page, int size){
        //page从0开始
        return frmUserService.queryPagedUsers(--page, size);
    }
}
