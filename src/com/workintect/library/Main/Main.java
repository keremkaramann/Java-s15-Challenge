package com.workintect.library.Main;

import com.workintect.library.Enum.Genre;
import com.workintect.library.models.*;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book(1, "Ham on Rye", new Author(1, "Charles Bukowski"), Genre.TRANSGRESSIONAL, 65.22));
        library.addBook(new Book(2, "Post Office", new Author(1, "Charles Bukowski"), Genre.TRANSGRESSIONAL, 75.22));

        library.addBook(new Book(3, "Choke", new Author(2, "Chuck Palahniuk"), Genre.TRANSGRESSIONAL, 25.22));
        library.addBook(new Book(4, "Fight Club", new Author(2, "Chuck Palahniuk"), Genre.TRANSGRESSIONAL, 25.22));

        library.addBook(new Book(88, "Kinyas ve Kayra", new Author(3, "Hakan Günday"), Genre.TRANSGRESSIONAL, 23.22));

        library.addBook(new Book(6, "Karamazov Brothers", new Author(4, "Fyodor Dostoyevski"), Genre.CLASSICS, 34.52));
        library.addBook(new Book(8, "Notes from UnderGround", new Author(4, "Fyodor Dostoyevski"), Genre.PSYCHOLOGICAL_FICTION, 37.52));
        library.addBook(new Book(9, "Anna Karenina", new Author(5, "Leo TOLSTOY"), Genre.CLASSICS, 17.92));
        library.addBook(new Book(10, "The Death of Ivan Ilych", new Author(5, "Leo TOLSTOY"), Genre.CLASSICS, 6.52));

        library.addBook(new Book(12, "Hamlet", new Author(6, "William Shakespeare"), Genre.TRAGEDIES, 68.12));
        library.addBook(new Book(13, "Macbeth", new Author(6, "William Shakespeare"), Genre.TRAGEDIES, 16.82));
        library.addBook(new Book(14, "Julius Caesar", new Author(6, "William Shakespeare"), Genre.TRAGEDIES, 7.22));

        library.addBook(new Book(15, "Kafka on the Shore", new Author(7, "Haruki Murakami"), Genre.PSYCHOLOGICAL_FICTION, 43.22));
        library.addBook(new Book(16, "Norwegian Wood", new Author(7, "Haruki Murakami"), Genre.PSYCHOLOGICAL_FICTION, 30.22));

        library.addBook(new Book(17, "The Count of Monte Cristo", new Author(8, "Alexandre Dumas"), Genre.ADVENTURE, 12.42));
        library.addBook(new Book(18, "The Three Musketeers", new Author(8, "Alexandre Dumas"), Genre.ADVENTURE, 20.22));

        library.addBook(new Book(19, "Harry Potter", new Author(9, "J.K. Rowling"), Genre.FANTASY, 40.22));
        library.addBook(new Book(20, "Lord of the Rings", new Author(10, "J.R.R. Tolkien"), Genre.FANTASY, 26.22));
        library.addBook(new Book(21, "Kayıp Tanrılar Ülkesi", new Author(11, "Ahmet Ümit"), Genre.CRIME_FICTION, 53.22));

        System.out.println("*********");

        Scanner scanner = new Scanner(System.in);

        Person user = null;
        int choice;

        do {
            try {
                System.out.println("Enter a number to do an action!");
                System.out.println("0 - Exit from the system!");
                System.out.println("1 - List all Books");
                System.out.println("2 - List all Books by Author");
                System.out.println("3 - List all Books by Genre");
                System.out.println("4 - List a Book by ID");
                System.out.println("5 - List a Book by Title");
                System.out.println("6 - Update a Book by ID");
                System.out.println("7 - Delete a Book by ID");
                System.out.println("8 - Borrow book");
                System.out.println("9 - Return book");
                System.out.println("10 - Add book");

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        System.out.println("Closing the program.");
                        break;
                    case 1:
                        library.printAllBooks();
                        break;
                    case 2:
                        System.out.println("Enter a author name and surname: ");
                        String authorName = scanner.nextLine();
                        library.displayBooksByAuthorName(authorName);
                        break;
                    case 3:
                        System.out.println("Enter valid Genre: ");
                        String genreName = scanner.nextLine();
                        library.getBooksByGenre(genreName);
                        break;
                    case 4:
                        System.out.println("Enter valid Book Id: ");
                        library.printAllBooks();
                        try {
                            long bookId = Long.parseLong(scanner.nextLine());
                            library.getBookById(bookId);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input please enter valid number for book id!");
                        }
                        break;
                    case 5:
                        System.out.println("Enter a book title to see the book info: ");
                        String bookTitle = scanner.nextLine();
                        library.showBookByTitle(bookTitle);
                        break;
                    case 6:
                        library.updateBook(scanner);
                        break;
                    case 7:
                        System.out.println("Enter a book id to delete the book: ");
                        try {
                            long bookId = Long.parseLong(scanner.nextLine());
                            library.deleteBook(bookId);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input please enter valid number for book id!");
                        }
                        break;
                    case 8:
                        try {
                            System.out.println("Please enter your library id: ");
                            long userId = scanner.nextLong();
                            scanner.nextLine();

                            System.out.println("Please enter your name or fullName: ");
                            String userName = scanner.nextLine();

                            User existingUser = library.findUserById(userId);
                            double userBalance;
                            if (existingUser != null) {
                                user = existingUser;
                            } else {
                                System.out.println("Please enter your balance: ");
                                userBalance = scanner.nextDouble();

                                if (userId <= 0 || userBalance < 0) {
                                    System.out.println("Invalid input. Please enter valid values for user ID and balance.");
                                    return;
                                }
                                user = new User(userId, userName, userBalance);
                                library.getAllUsers().add((User) user);  // Add the new user to the library
                            }

                            library.printAllBooks();
                            System.out.println("Enter the ID of the book you want to borrow: ");
                            long bookIdToBorrow = scanner.nextLong();
                            scanner.nextLine();
                            ((User) user).borrowBook(library, bookIdToBorrow);

                            System.out.println("Do you want to see your recipe type (Y/N)?");
                            String yesOrNo=scanner.nextLine().trim();
                            if(yesOrNo.toLowerCase().startsWith("y")){
                                ((User) user).recipe();
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter valid numeric values for user ID and balance.");
                            scanner.nextLine();
                        }
                        break;
                    case 9:
                        if (user != null) {
                            library.printAllBooks();
                            System.out.println("Please enter the book Id for return");
                            long returnBookId = scanner.nextLong();
                            scanner.nextLine();
                            ((User) user).returnBook(library, returnBookId);
                        } else {
                            System.out.println("No user created yet. Please create a user first.");
                        }
                        break;
                    case 10:
                        System.out.println("Id of the new book");
                        long bookId = scanner.nextLong();
                        scanner.nextLine();

                        System.out.println("Title of the new book: ");
                        String title = scanner.nextLine();

                        System.out.println("Author ID:");
                        long newAuthorId = scanner.nextLong();
                        scanner.nextLine();

                        System.out.println("Author Name:");
                        String newAuthorName = scanner.nextLine();

                        System.out.println("Genre:");
                        String newGenreName = scanner.nextLine();

                        System.out.println("Price:");
                        double newBookPrice = scanner.nextDouble();

                        Author newAuthor = new Author(newAuthorId, newAuthorName);
                        Genre newGenre = Genre.valueOf(newGenreName.toUpperCase());

                        Book addNewBook = new Book(bookId, title, newAuthor, newGenre, newBookPrice);

                        library.addBook(addNewBook);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter an existing integer number!");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number!!");
                scanner.nextLine(); // clear the invalid input
                choice = -1; // escape infinite loop
            }
        } while (choice != 0);
        scanner.close();
    }
}