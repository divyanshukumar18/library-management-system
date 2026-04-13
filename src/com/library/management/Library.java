package com.library.management;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Manages books and users for the library.
 */
public class Library {
    private final Map<String, Book> books;
    private final Map<String, User> users;

    public Library() {
        this.books = new LinkedHashMap<>();
        this.users = new LinkedHashMap<>();
    }

    public void addBook(Book book) {
        Objects.requireNonNull(book, "Book cannot be null");
        books.put(book.getId(), book);
    }

    public void addUser(User user) {
        Objects.requireNonNull(user, "User cannot be null");
        users.put(user.getId(), user);
    }

    public Book findBookById(String bookId) {
        return books.get(bookId);
    }

    public User findUserById(String userId) {
        return users.get(userId);
    }

    public boolean issueBook(String bookId, String userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);
        if (book == null || user == null) {
            return false;
        }
        return user.borrowBook(book);
    }

    public boolean returnBook(String bookId, String userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);
        if (book == null || user == null) {
            return false;
        }
        return user.returnBook(book);
    }

    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(books.values().stream().collect(Collectors.toList()));
    }

    public List<User> getAllUsers() {
        return Collections.unmodifiableList(users.values().stream().collect(Collectors.toList()));
    }

    public List<Book> getAvailableBooks() {
        return books.values().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Book> getIssuedBooks() {
        return books.values().stream()
                .filter(book -> !book.isAvailable())
                .collect(Collectors.toList());
    }
}
