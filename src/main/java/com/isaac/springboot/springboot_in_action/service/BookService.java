package com.isaac.springboot.springboot_in_action.service;

import com.isaac.springboot.springboot_in_action.entity.Book;

import java.util.List;

public interface BookService {
    Book getBookById(String id);

    List<Book> search(String key);

    List<Book> search(String key, int page);
}
