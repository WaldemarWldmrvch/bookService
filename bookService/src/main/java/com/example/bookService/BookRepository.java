package com.example.bookService;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Indexed;

@Indexed
public interface BookRepository extends CrudRepository<Book, Long> {
}
