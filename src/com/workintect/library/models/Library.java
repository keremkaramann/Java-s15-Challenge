package com.workintect.library.models;

import com.workintect.library.Enum.Genre;


import java.util.*;

public class Library implements LibraryItems{
    private Set<Book> allBooks;
    private List<User> allUsers;

    public Library() {
        this.allBooks = new TreeSet<>();
        this.allUsers = new ArrayList<>();
    }

    public Set<Book> getAllBooks() {
        return allBooks;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void addBook(Book book) {
        //check id
        if (allBooks.stream().anyMatch(regsiteredBook -> regsiteredBook.getBook_id() == book.getBook_id())) {
            System.out.println("Error:" + book.getBook_id() + " this book id already exists.");
            return;
        }
        //check title
        if (allBooks.stream().anyMatch(regsiteredBook -> regsiteredBook.getTitle().equalsIgnoreCase(book.getTitle()))) {
            System.out.println("Error: There is already a book named " + book.getTitle());
            return;
        }
        allBooks.add(book);
        System.out.println("Book added successfully!");
    }

    public Book[] printAllBooks() {
        Book[] books = allBooks.toArray(new Book[0]);
        for (Book book : books) {
            System.out.println(book);
        }
        return books;
    }

    public Book[] getBooksByGenre(String genreInput) {
        //guarding
        if (genreInput == null || genreInput.trim().isEmpty()) {
            System.out.println("Invalid genre input");
            return new Book[0];
        }
        try {
            Genre genre = Genre.valueOf(genreInput.toUpperCase());
            Set<Book> booksByGenre = new TreeSet<>();
            for (Book book : allBooks) {
                if (book.getGenre() == genre) {
                    booksByGenre.add(book);
                }
            }

            if (!booksByGenre.isEmpty()) {
                System.out.println("\nBooks in the genre " + genre + ":");
                for (Book book : booksByGenre) {
                    System.out.println(book.getTitle());
                    System.out.println("********");
                }
            } else {
                System.out.println("No books found for the specified genre.");
            }

            return booksByGenre.toArray(new Book[0]);//if genre is invalid or no books found
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid genre: " + genreInput);
            return new Book[0];
        }
    }

    public void displayBooksByAuthorName(String authorName) {
        //guarding
        if (authorName == null || authorName.trim().isEmpty()) {
            System.out.println("Invalid author name.");
            return;
        }
        Set<Book> booksByAuthor = new TreeSet<>();
        for (Book book : allBooks) {
            if (book.getAuthor().getFullName().equalsIgnoreCase(authorName)) {
                booksByAuthor.add(book);
            }
        }

        if (!booksByAuthor.isEmpty()) {
            System.out.println("\nBooks by " + authorName.toUpperCase(Locale.ROOT) + ":");
            for (Book book : booksByAuthor) {
                System.out.println(book.getTitle());
            }
        } else {
            System.out.println("No books found for the specified author.");
        }
    }
    public boolean deleteBook(long bookId) {
        for (Book book : allBooks) {
            if (book.getBook_id() == bookId && book.isBorrowed()) {
                allBooks.remove(book);
                System.out.println("Book with ID " + bookId + " removed successfully.");
                return true;
            }
        }
        System.out.println("Book with ID "   + bookId + " not found.");
        return false;
    }
@Override
    public Book findBookById(long bookId) {
        for (Book book : allBooks) {
            if (book.getBook_id() == bookId) {
                return book;
            }
        }
        return null;
    }
@Override
    public User findUserById(long userId) {
        for (User user : allUsers) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
    public void updateBook(Scanner scanner) {
        try {
            System.out.println("Enter the Id of Book that you want to update: ");
            long bookId = scanner.nextInt();
            scanner.nextLine();

            Book booksToUpdate = findBookById(bookId);

            if (booksToUpdate != null) {
                System.out.println("Enter the new title of the book : currentTitle is: " + booksToUpdate.getTitle());
                String newTitle = scanner.nextLine().trim();
                if (!newTitle.isEmpty()) {
                    booksToUpdate.setTitle(newTitle);
                }

                System.out.println("Enter the new author current: " + booksToUpdate.getAuthor());
                String newAuthor = scanner.nextLine().trim();
                if (!newAuthor.isEmpty()) {
                    booksToUpdate.setAuthor(new Author(1, newAuthor));
                }
                System.out.println("Enter the new genre current: " + booksToUpdate.getGenre());
                String newGenre = scanner.nextLine().trim();
                if (!newGenre.isEmpty()) {
                    Genre genre = Genre.valueOf(newGenre.toUpperCase());
                    booksToUpdate.setGenre(genre);
                }
                System.out.println("Enter the new price current: " + booksToUpdate.getPrice());
                String newPrice = scanner.nextLine().trim();
                if (!newPrice.isEmpty()) {
                    try {
                        double newPriceBook = Double.parseDouble(newPrice);
                        booksToUpdate.setPrice(newPriceBook);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid numeric value for the new price!");
                    }
                }
                System.out.println("Book updated successfully");
                getBookById(bookId);
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for Book ID!");
        }
    }

    public void getBookById(long bookId) {
        boolean found = false;
        for (Book book : allBooks) {
            if (book.getBook_id() == bookId) {
                System.out.println(book);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book with ID " + bookId + " not found.");
        }
    }

    public void showBookByTitle(String bookTitle) {
        //guarding
        if (bookTitle == null || bookTitle.trim().isEmpty()) {
            System.out.println("Invalid author name.");
        }
        Set<Book> booksByTitle = new TreeSet<>();
        for (Book book : allBooks) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                booksByTitle.add(book);
            }
        }
        if (!booksByTitle.isEmpty()) {
            System.out.println("\nBooks with the title '" + bookTitle + "':");
            for (Book book : booksByTitle) {
                System.out.println(book);
                System.out.println("********");
            }
        } else {
            System.out.println("No books found with the specified title.");
        }
    }
}
