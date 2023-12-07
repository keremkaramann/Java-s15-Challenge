package com.workintect.library.models;

import java.util.Set;
import java.util.TreeSet;

public class Author extends Person {

    private Set<Book> authorBooks;

    public Author(long id, String fullName) {
        super(id, fullName);
        this.authorBooks = new TreeSet<>();
    }

    public Set<Book> getBooks() {
        return authorBooks;
    }

    public void setBooks(Set<Book> books) {
        this.authorBooks = books;
    }

    public void addBook(Book book) {
        authorBooks.add(book);
    }

    @Override
    public String toString() {
        return "Author{" +
                "books=" + authorBooks +
                '}';
    }
}
