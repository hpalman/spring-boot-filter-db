package com.example.filterdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.filterdb.entity.Author;
import com.example.filterdb.entity.Book;
import com.example.filterdb.service.LibraryService;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    // CRUD operations for Author
    @PostMapping("/authors")
    public Author createAuthor(@RequestBody Author author) {
        return libraryService.createAuthor(author);
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return libraryService.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    public Optional<Author> getAuthorById(@PathVariable String id) {
        return libraryService.getAuthorById(id);
    }

    @PutMapping("/authors/{id}")
    public Author updateAuthor(@PathVariable String id, @RequestBody Author author) {
        return libraryService.updateAuthor(id, author);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable String id) {
        libraryService.deleteAuthor(id);
    }

    // CRUD operations for Book
    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return libraryService.createBook(book);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getBookById(@PathVariable String id) {
        return libraryService.getBookById(id);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book book) {
        return libraryService.updateBook(id, book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable String id) {
        libraryService.deleteBook(id);
    }

    // Add a book to an author
    @PostMapping("/authors/{authorId}/books")
    public void addBookToAuthor(@PathVariable String authorId, @RequestBody Book book) {
        /// libraryService.addBookToAuthor(authorId, book);
    }
}
