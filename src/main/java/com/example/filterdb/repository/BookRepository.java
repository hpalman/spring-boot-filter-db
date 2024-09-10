package com.example.filterdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filterdb.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
