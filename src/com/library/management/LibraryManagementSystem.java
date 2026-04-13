package com.library.management;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point for the library management system.
 */
public class LibraryManagementSystem {
    private final Library library;
    private final Scanner scanner;

    public LibraryManagementSystem() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
        loadSampleData();
    }

    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();
        system.run();
    }

    private void run() {
        printHeader();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInteger("Choose an option: ");
            switch (choice) {
                case 1 -> listBooks();
                case 2 -> listUsers();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> showUserReport();
                case 6 -> addBook();
                case 7 -> addUser();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting the library system. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printHeader() {
        System.out.println("=======================================");
        System.out.println("      Library Management System       ");
        System.out.println("=======================================");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. List all books");
        System.out.println("2. List all users");
        System.out.println("3. Issue a book");
        System.out.println("4. Return a book");
        System.out.println("5. Show user report");
        System.out.println("6. Add a new book");
        System.out.println("7. Add a new user");
        System.out.println("0. Exit");
    }

    private void listBooks() {
        List<Book> books = library.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found in the library.");
            return;
        }
        System.out.println("Available book collection:");
        books.forEach(book -> System.out.println("- " + book));
    }

    private void listUsers() {
        List<User> users = library.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users registered in the library.");
            return;
        }
        System.out.println("Registered users:");
        users.forEach(user -> System.out.println("- " + user));
    }

    private void issueBook() {
        String bookId = readString("Enter book ID to issue: ");
        String userId = readString("Enter user ID who will borrow the book: ");
        boolean success = library.issueBook(bookId, userId);
        if (success) {
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Unable to issue the book. Verify that the book and user exist and the book is available.");
        }
    }

    private void returnBook() {
        String bookId = readString("Enter book ID to return: ");
        String userId = readString("Enter user ID returning the book: ");
        boolean success = library.returnBook(bookId, userId);
        if (success) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Unable to return the book. Verify that the book was issued to this user.");
        }
    }

    private void showUserReport() {
        String userId = readString("Enter user ID for report: ");
        User user = library.findUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println("User report for " + user.getName() + " (" + user.getId() + "):");
        List<Book> borrowedBooks = user.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("This user has not borrowed any books.");
            return;
        }
        borrowedBooks.forEach(book -> System.out.println("- " + book));
    }

    private void addBook() {
        String bookId = readString("Enter new book ID: ");
        String title = readString("Enter book title: ");
        String author = readString("Enter book author: ");
        library.addBook(new Book(bookId, title, author));
        System.out.println("Book added successfully.");
    }

    private void addUser() {
        String userId = readString("Enter new user ID: ");
        String name = readString("Enter user name: ");
        library.addUser(new User(userId, name));
        System.out.println("User added successfully.");
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid numeric value.");
            }
        }
    }

    private void loadSampleData() {
        library.addBook(new Book("B001", "Effective Java", "Joshua Bloch"));
        library.addBook(new Book("B002", "Clean Code", "Robert C. Martin"));
        library.addBook(new Book("B003", "Head First Design Patterns", "Eric Freeman"));

        library.addUser(new User("U001", "Alice"));
        library.addUser(new User("U002", "Bob"));
        library.addUser(new User("U003", "Carol"));
    }
}
