package com.southwind.springboottest.repository;

import com.southwind.springboottest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {




    // @Query("from Book b where b.author=:author")
//    List<Book> findBook(@Param("author") String author);



    List<Book> findByAuthor(String author);
}
