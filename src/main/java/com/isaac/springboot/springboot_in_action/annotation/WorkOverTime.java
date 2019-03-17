package com.isaac.springboot.springboot_in_action.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 校验框架 自定义校验
 * @Constraint定义用什么类实现校验逻辑
 */
@Constraint(validatedBy = {WorkOverTimeValidator.class})
@Documented
@Target({ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkOverTime {
    String message() default "加班时间过长，不能超过{max}小时";
    int max() default 5;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
