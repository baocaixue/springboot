package com.isaac.springboot.springboot_in_action.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SpringSessionController {
    public static final Log LOG = LogFactory.getLog(SpringSessionController.class);

    @RequestMapping("putSession")
    public @ResponseBody String putSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        LOG.info("session class is " + session.getClass());//org.apache.catalina.session.StandardSessionFacade
        LOG.info("session id is " + session.getId());
        String name = "isaac";
        session.setAttribute("user",name);
        return "hey," + name;
    }
}
