package com.isaac.springboot.springboot_in_action.springbootunittest;

import com.isaac.springboot.springboot_in_action.controller.FrmUserController;
import com.isaac.springboot.springboot_in_action.service.FrmUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//@RunWith(SpringRunner.class)
//@WebMvcTest(FrmUserController.class)
public class ControllerTest {
    //@Autowired
    private MockMvc mvc;

    //@Autowired
    private FrmUserService service;
    //@Test
    public void test() throws Exception {
        ResultActions perform = mvc.perform(post("localhost://isaac/querySortedUsers"));
        perform.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

