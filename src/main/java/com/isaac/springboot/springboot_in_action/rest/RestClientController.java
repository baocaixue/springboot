package com.isaac.springboot.springboot_in_action.rest;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isaac.springboot.springboot_in_action.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestClientController {
    @Autowired
    private RestTemplateBuilder RestTemplateBuilder;

    @RequestMapping("restclient/book/{id}")
    public String getLogById(@PathVariable String id) throws Exception{
        Book book = null;
        RestTemplate restTemplate = RestTemplateBuilder.build();
        Map<String, Object> paras = new HashMap<>();
        paras.put("id",id);
        String str = restTemplate.getForObject("http://127.0.0.1:9200/product/book/{id}", String.class, paras);

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(str);
        //只对返回json的source字段感兴趣
        TreeNode root = mapper.readTree(parser);
        TreeNode sourceNode = root.get("_source");
        //映射到Book对象
        book = mapper.convertValue(sourceNode, Book.class);
        return book.getMessage();
    }
}
