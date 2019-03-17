package com.isaac.springboot.springboot_in_action.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局定制化Spring Boot mvc特性
 */
@Configuration
public class MvcConfigurer implements WebMvcConfigurer{
    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionHandlerInterceptor()).addPathPatterns("/admin/**");
    }

    //跨域访问配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许所有跨域访问
        registry.addMapping("/**");

        registry.addMapping("/api/**").allowedOrigins("http://domain.com").allowedMethods("GET","POST");
    }

    //格式化
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    //URI到视图的映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //没必要为一个URL指定Controller时
//        registry.addViewController("/index.html").setViewName("/index.html");
//        registry.addRedirectViewController("/**/*.do","/index.html");
    }

    //...其他更多全局定制接口
}
