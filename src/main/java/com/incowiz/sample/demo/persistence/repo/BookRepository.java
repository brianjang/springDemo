package com.incowiz.sample.demo.persistence.repo;

import com.incowiz.sample.demo.persistence.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
