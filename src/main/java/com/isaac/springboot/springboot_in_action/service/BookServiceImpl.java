package com.isaac.springboot.springboot_in_action.service;

import com.isaac.springboot.springboot_in_action.dao.BookDao;
import com.isaac.springboot.springboot_in_action.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired(required = false)
    private BookDao bookDao;

    @Override
    public Book getBookById(String id) {
        Optional<Book> opt = bookDao.findById(id);
        return opt.get();
    }

    @Override
    public List<Book> search(String key) {
        return bookDao.getByMessage(key);
    }

    @Override
    public List<Book> search(String key, int page) {
        int numberOfPage = 5;
        PageRequest request = PageRequest.of(page, numberOfPage);
        Page<Book> pages = bookDao.getByMessage(key, request);
        return pages.getContent();
    }
}
