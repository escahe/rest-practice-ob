package com.escahe.restdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escahe.restdemo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    
}