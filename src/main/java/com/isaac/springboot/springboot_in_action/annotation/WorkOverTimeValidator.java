package com.isaac.springboot.springboot_in_action.annotation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WorkOverTimeValidator implements ConstraintValidator<WorkOverTime,Integer>{
    WorkOverTime work;
    int max;
    @Override
    public void initialize(WorkOverTime work) {
        this.work = work;
        max = work.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value == null)
            return true;
        return value < max;
    }
}
