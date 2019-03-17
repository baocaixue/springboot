package com.isaac.springboot.springboot_in_action.controller;

import com.isaac.springboot.springboot_in_action.entity.Book;
import com.isaac.springboot.springboot_in_action.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ElasticDataController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/springdata/book/{id}")
    public Book getBookById(@PathVariable String id){
        return bookService.getBookById(id);
    }

    @RequestMapping("/springdata/search/{key}")
    public List<Book> search(@PathVariable String key){
        return bookService.search(key);
    }

    @RequestMapping("/springdata/search/{key}/{page}")
    public List<Book> search(@PathVariable int page,@PathVariable String key){
        return bookService.search(key,page);
    }
}
