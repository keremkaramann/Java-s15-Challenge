package com.workintect.library.models;

import com.workintect.library.Enum.Genre;

import java.util.Locale;
import java.util.Objects;

public class Book implements Comparable<Book> {
    private long book_id;
    private String title;
    private Author author; //composition
    private Genre genre; //enums
    private double price;
    private boolean isBorrowed;


    public Book(long book_id, String title, Author author, Genre genre, double price) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.isBorrowed = false;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_id == book.book_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id);
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.title);
    }

    @Override
    public String toString() {
        return
                "Book_ID: " + book_id +
                        ", Name: '" + title + '\'' +
                        ", Author: " + author.getFullName().toUpperCase(Locale.ROOT) +
                        ", Genre: " + genre +
                        ", Price: " + price +
                        ", IsBorrowed: " + isBorrowed
                ;
    }
}
