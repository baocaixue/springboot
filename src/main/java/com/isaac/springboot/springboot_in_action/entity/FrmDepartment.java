package com.isaac.springboot.springboot_in_action.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FrmDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrmDepartment that = (FrmDepartment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "FrmDepartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
