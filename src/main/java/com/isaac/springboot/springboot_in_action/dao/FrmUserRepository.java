package com.isaac.springboot.springboot_in_action.dao;

import com.isaac.springboot.springboot_in_action.entity.FrmUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrmUserRepository extends JpaRepository<FrmUser,Integer> {
}
