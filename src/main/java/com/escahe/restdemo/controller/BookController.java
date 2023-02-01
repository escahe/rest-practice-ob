package com.escahe.restdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escahe.restdemo.model.Book;
import com.escahe.restdemo.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok().body(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok().body(bookService.getById(id));
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(bookService.save(book));
    }
}
