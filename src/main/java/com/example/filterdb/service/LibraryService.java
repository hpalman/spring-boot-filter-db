package com.example.filterdb.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.filterdb.entity.Author;
import com.example.filterdb.entity.Book;
import com.example.filterdb.repository.AuthorRepository;
import com.example.filterdb.repository.BookRepository;

@Service
public class LibraryService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    // CRUD operations for Author

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(String id) {
        return authorRepository.findById(id);
    }

    public Author updateAuthor(String id, Author authorDetails) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setName(authorDetails.getName());
            return authorRepository.save(author);
        }
        return null;
    }

    public void deleteAuthor(String id) {
        authorRepository.deleteById(id);
    }

    // CRUD operations for Book

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(String id, Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            return bookRepository.save(book);
        }
        return null;
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    public void addBookToAuthor(Long authorId, Book book) {
        // Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        // if (optionalAuthor.isPresent()) {
        //     Author author = optionalAuthor.get();
        //     author.addBook(book);
        //     authorRepository.save(author);
        // }
    }
}
