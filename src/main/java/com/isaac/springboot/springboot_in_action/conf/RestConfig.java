package com.isaac.springboot.springboot_in_action.conf;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig implements RestTemplateCustomizer{
    @Override
    public void customize(RestTemplate restTemplate) {
        SimpleClientHttpRequestFactory jdkHttp = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        jdkHttp.setConnectTimeout(1000);
    }
}
