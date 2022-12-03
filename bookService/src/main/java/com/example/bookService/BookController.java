package com.example.bookService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class BookController {
    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/library/{id}")
    public Book getBook (@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return repository.findById(id).get();
        }
    }

    @GetMapping("/library/all")
    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>((Collection) repository.findAll());
        return allBooks;
    }

    @PostMapping("/library/new")
    public String saveBook(@Valid @RequestBody Book book) {
        repository.save(book);
        return String.format("Отлично! Теперь книга \"%s\" автора \"%s\" расположена на %d полке.",
                book.getName(), book.getAuthor(), book.getId());
    }

    @DeleteMapping("/library/{id}")
    public String deleteBook(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            repository.deleteById(id);
            return String.format("Книга c полки %d была успешно удалена.", id);
        }
    }
}
