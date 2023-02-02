package com.escahe.restdemo.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escahe.restdemo.model.Book;
import com.escahe.restdemo.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok().body(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book bookFound = bookService.getById(id);
        return ResponseEntity.status(
                Objects.isNull(bookFound)?HttpStatus.NOT_FOUND:
                HttpStatus.FOUND
            ).body(bookFound);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Book book) {
        if(Objects.nonNull(book.getId())){
            log.warn("Trying to create a book with an id");
            return ResponseEntity.badRequest().build();
        }
            
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(bookService.save(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        if(bookService.existById(id)){
            bookService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();        
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Book book) {
        if(Objects.isNull(book.getId())){
            log.warn("Trying to update a book with no id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookService.update(book));
    }
}
