package com.isaac.springboot.springboot_in_action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringbootInActionApplicationTests {
	//@Autowired
	private StringRedisTemplate stringRedisTemplate;

	//@Autowired
	//@Qualifier("strKeyRedisTemplate")
	private RedisTemplate redisTemplate;

	public static final User user = new User("isaac",18,"13262270185");

	//@Test
	//@DirtiesContext//提示spring重新加载上下文
	public void contextLoads() {
		stringRedisTemplate.opsForValue().set("13262270185","world");
		System.out.println(stringRedisTemplate.keys("*hello"));
	}

	//@Test
	public void test(){
		redisTemplate.opsForValue().set("13262270185",user);
		Object u = redisTemplate.opsForValue().get("13262270185");
		System.out.println(((User) u));
	}

	private  static class User implements Serializable{
		private String name;
		private Integer age;
		private String phone;

		public User(String name, Integer age, String phone) {
			this.name = name;
			this.age = age;
			this.phone = phone;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		@Override
		public String toString() {
			return "User{" +
					"name='" + name + '\'' +
					", age=" + age +
					", phone='" + phone + '\'' +
					'}';
		}
	}

}
