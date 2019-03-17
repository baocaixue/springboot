package com.isaac.springboot.springboot_in_action.springbootunittest;

import com.isaac.springboot.springboot_in_action.entity.FrmUser;
import com.isaac.springboot.springboot_in_action.service.FrmUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 单元测试service有3个需要考虑的地方
 *  可重复的测试，希望数据能回滚——springboot默认回滚
 *  依赖其他未开发的component——mockito
 *  数据库，模拟测试场景——@Sql
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserServiceTest {
    //@Autowired
    private FrmUserService userService;

    //@Test
    //@Rollback
    //@Transactional
    public void test(){
        FrmUser user = new FrmUser();
        user.setName("unitTest");
        user.setDepartmentId(2);
        user.setCreateTime(new Date());
        Integer i = userService.addFrmUser(user);
        Assert.assertSame(11,i);
    }
}
