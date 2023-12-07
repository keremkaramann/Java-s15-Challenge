package com.workintect.library.models;


import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class User extends Person {
    private int borrowedBooksNum;
    private double balance;
    private List<Book> borrowedBooks;

    public User(long id, String fullName, double balance) {
        super(id, fullName);
        this.borrowedBooksNum = 0;
        this.balance = balance;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getBorrowedBooksNum() {
        return borrowedBooksNum;
    }

    public void setBorrowedBooksNum(int borrowedBooksNum) {
        this.borrowedBooksNum = borrowedBooksNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public boolean bookLimit() {
        return borrowedBooksNum <= 5;
    }

    public void borrowBook(Library library, long bookId) {
        Book book = library.findBookById(bookId);
        if (book != null) {
            if (bookLimit()) {
                if (!book.isBorrowed()) {
                    double bookPrice = book.getPrice();
                    if (getBalance() >= book.getPrice()) {
                        borrowedBooks.add(book);
                        borrowedBooksNum++;
                        book.setBorrowed(true);
                        balance -= bookPrice;
                        System.out.println( book.getTitle().toUpperCase() + " borrowed successfully by " + getFullName().toUpperCase());
                        System.out.println("**********");
                    } else {
                        System.out.println("Sorry, your balance is not enough to borrow this book.");
                    }
                } else {
                    System.out.println("This book is already borrowed!");
                }
            } else {
                System.out.println("Sorry, you have reached the max book limit.");
            }
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }
    }

    public void recipe() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed yet.");
        } else {
            System.out.println("Recipe for: " + getFullName().toUpperCase(Locale.ROOT));
            Book latestBorrowedBook = borrowedBooks.get(borrowedBooks.size() - 1);

            System.out.println("Time of Purchase: " + LocalDate.now());
            System.out.println("Borrowed books: " + getBorrowedBooksNum());
            System.out.println("Book Title: " + latestBorrowedBook.getTitle());
            System.out.println("Book Price: " + latestBorrowedBook.getPrice() + "$");
            System.out.println("Remaining Balance: " + getBalance() + "$");
            System.out.println("********");
        }
    }

    public void returnBook(Library library, long bookId) {
        Book book = library.findBookById(bookId);

        if (book != null && borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            borrowedBooksNum--;
            book.setBorrowed(false);
            balance += book.getPrice();
            System.out.println("Recipe for: " + getFullName().toUpperCase(Locale.ROOT));
            System.out.println("Time of Return: " + LocalDate.now());
            System.out.println("Borrowed books: " + getBorrowedBooksNum());
            System.out.println("Book Title: " + book.getTitle());
            System.out.println("Book Price: " + book.getPrice() + "$");
            System.out.println("Remaining Balance: " + getBalance() + "$");
        } else {
            System.out.println("Cannot return the book. It is not currently borrowed by this user.");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "borrowedBooksNum=" + borrowedBooksNum +
                ", balance=" + balance +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
