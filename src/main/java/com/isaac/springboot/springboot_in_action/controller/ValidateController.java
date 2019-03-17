package com.isaac.springboot.springboot_in_action.controller;

import com.isaac.springboot.springboot_in_action.entity.WorkInfo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 验证框架
 */
@RestController
public class ValidateController {

    @RequestMapping("addWorkInfo")
    public void addWorkInfo(@Validated({WorkInfo.Add.class}) WorkInfo workInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                System.out.println(error.getObjectName() + "," + error.getCode() + "," + error.getDefaultMessage());
            }
        } else {
            System.out.println("Success validate workInfo is " + workInfo);
        }
    }
}
