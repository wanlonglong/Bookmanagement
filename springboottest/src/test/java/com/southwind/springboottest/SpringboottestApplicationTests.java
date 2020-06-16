package com.southwind.springboottest;

import com.southwind.springboottest.entity.Book;
import com.southwind.springboottest.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class SpringboottestApplicationTests {
    @Autowired
    private BookRepository repository;

    @Test
    void contextLoads() {
        PageRequest pageRequest = PageRequest.of(0,6);
        Page<Book> page = repository.findAll(pageRequest);
    }



    @Test
    void findById(){
        Book book = repository.findById(2).get();
        System.out.println(book);
    }



    @Test
    void delete(){
        repository.deleteById(1);
    }
}
