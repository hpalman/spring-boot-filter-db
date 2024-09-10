package com.example.filterdb.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Author {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    //// One author can have multiple books
    //@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Book> books = new ArrayList<>();
    //
    //// Constructors, getters, and setters
    //
    //public Author() {}
    //
    //public Author(String name) {
    //    this.name = name;
    //}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public List<Book> getBooks() {
    //    return books;
    //}
    //
    //public void setBooks(List<Book> books) {
    //    this.books = books;
    //}
    //
    //// Helper method to add a book
    //public void addBook(Book book) {
    //    books.add(book);
    //    book.setAuthor(this);
    //}
    //
    //public void removeBook(Book book) {
    //    books.remove(book);
    //    book.setAuthor(null);
    //}
}
