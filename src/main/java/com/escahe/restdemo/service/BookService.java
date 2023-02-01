package com.escahe.restdemo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escahe.restdemo.model.Book;
import com.escahe.restdemo.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List <Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book update(Book book) {
        Book bookToSave = getById(book.getId());
        if(Objects.nonNull(book.getAuthor())) bookToSave.setAuthor(book.getAuthor());
        if(Objects.nonNull(book.getAvailable())) bookToSave.setAvailable(book.getAvailable());
        if(Objects.nonNull(book.getPages())) bookToSave.setPages(book.getPages());
        if(Objects.nonNull(book.getPrice())) bookToSave.setPrice(book.getPrice());
        if(Objects.nonNull(book.getReleaseDate())) bookToSave.setReleaseDate(book.getReleaseDate());
        if(Objects.nonNull(book.getTitle())) bookToSave.setTitle(book.getTitle());
        return save(bookToSave);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
