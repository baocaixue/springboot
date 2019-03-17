package com.isaac.springboot.springboot_in_action.dao;

import com.isaac.springboot.springboot_in_action.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDao extends CrudRepository<Book, String> {
    Page<Book> getByMessage(String key, PageRequest request);
    List<Book> getByMessage(String key);
}
