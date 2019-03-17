package com.isaac.springboot.springboot_in_action.entity;

import com.isaac.springboot.springboot_in_action.annotation.WorkOverTime;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * springboot验证框架实体类
 */
public class WorkInfo {
    //更新校验组
    public interface Update {
    }

    ;

    //新增校验组
    public interface Add {
    }

    ;

    @NotNull(groups = Update.class)
    @Null(groups = Add.class)
    private String id;

    @Size(min = 3, max = 20, groups = {Update.class, Add.class})
    @NotBlank
    private String name;

    @Email(groups = {Update.class, Add.class})
    private String email;

    @Range(min = 0, max = 9, groups = {Update.class, Add.class})
    private Integer level;

    @Pattern(regexp = "^(remark:).*", groups = {Update.class, Add.class})
    private String remark;

    @WorkOverTime(max = 3, groups = {Update.class, Add.class})
    private Integer worktime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getWorktime() {
        return worktime;
    }

    public void setWorktime(Integer worktime) {
        this.worktime = worktime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkInfo workInfo = (WorkInfo) o;
        return Objects.equals(id, workInfo.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "WorkInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", level=" + level +
                ", remark='" + remark + '\'' +
                ", worktime=" + worktime +
                '}';
    }
}
