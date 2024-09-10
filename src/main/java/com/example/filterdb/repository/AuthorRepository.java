package com.example.filterdb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filterdb.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
}
