package com.isaac.springboot.springboot_in_action;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class SpringbootInActionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootInActionApplication.class, args);
	}

}
