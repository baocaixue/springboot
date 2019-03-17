package com.isaac.springboot.springboot_in_action.junit;

import org.junit.*;

public class JUnitTest {
    @Test
    public void test(){
        int a = 10;
        int b = 9;
        int result = a - b;
        Assert.assertEquals(1,result);
    }

    @BeforeClass
    public static void beforeClassTest(){
        System.out.println("单元测试开始之前执行初始化...");
        System.out.println("-------------------------");
    }

    @Before
    public void beforeTest(){
        System.out.println("单元测试方法之前执行");
    }

    @After
    public void afterTest(){
        System.out.println("单元测试方法之后执行");
    }

    @AfterClass
    public static void afterClassTest(){
        System.out.println("--------------------------");
        System.out.println("单元测试结束之后执行...");
    }
}
