package com.southwind.springboottest;

import com.southwind.springboottest.entity.Book;
import com.southwind.springboottest.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

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
    void findByAuthor(){
        Book book1= new Book(111,"lksdfsdfds","dfdf");
        Book book= new Book(11,"lk","dfdf");

        repository.save((book));
        repository.save((book1));

        //  repository.save((new Book(111,"AAA", "book1")));
        List<Book> book2 = repository.findByAuthor("dfdf");
        System.out.println(book2);
    }

    @Test
    void delete(){
        repository.deleteById(1);
    }
}
