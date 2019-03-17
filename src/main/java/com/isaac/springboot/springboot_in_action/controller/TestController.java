package com.isaac.springboot.springboot_in_action.controller;

import com.isaac.springboot.springboot_in_action.conf.ZookeeperConfig;
import com.isaac.springboot.springboot_in_action.entity.FrmUser;
import com.isaac.springboot.springboot_in_action.entity.User;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    private RedisTemplate redisTemplate;
    //@Autowired
    private CuratorFramework client;

    @PostMapping(path = "test/{id}/{name}")
    public @ResponseBody
    String test(@PathVariable String id, @PathVariable String name) {
        return "SUCCESS," + id + ";" + name;
    }

    @RequestMapping(path = "users/{userId}")
    public ModelAndView getUser(@PathVariable String userId, ModelAndView view){
        User user = new User();
        user.setId(userId);
        view.addObject("user",user);
        view.setViewName("/userInfo.html");
        return view;
    }

    /**
     * javaBean接收http参数 @RequestParam定义映射关系 注解参数如下：
     * value http参数名称
     * required 参数是否必须
     * defaultValue http参数没有提供的话指定一个默认字符串
     */
    @RequestMapping("getUser1")
    public @ResponseBody User getUser1(User user){
        return user;
    }

    //json
    @RequestMapping("getUser2")
    public @ResponseBody User getUser2(@RequestBody User user){
        return user;
    }


    //common
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"),true));
//        binder.addCustomFormatter(new DateFormatter("yyyy/MM/dd"));
    }

    @RequestMapping("config")
    public @ResponseBody
    FrmUser getConfig(){
        FrmUser user = new FrmUser();
        user.setName("isaac");
        redisTemplate.opsForValue().set("test",user);
        return user;
    }

    @RequestMapping("regist")
    public @ResponseBody String aaatest(){
        try {
            ZookeeperConfig.registerService(client);
//            client.create().forPath("/test",new byte[0]);
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "Fail";
        }
    }

    @RequestMapping("use")
    public @ResponseBody ServiceInstance<Map> atest(){
        try {
            ServiceInstance<Map> book = ZookeeperConfig.findService(client, "book");
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
