package com.library.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a library user.
 */
public class User {
    private final String id;
    private String name;
    private final List<Book> borrowedBooks;

    public User(String id, String name) {
        this.id = Objects.requireNonNull(id, "User id cannot be null");
        this.name = Objects.requireNonNull(name, "User name cannot be null");
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "User name cannot be null");
    }

    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    public boolean borrowBook(Book book) {
        Objects.requireNonNull(book, "Book cannot be null");
        if (!book.issue()) {
            return false;
        }
        borrowedBooks.add(book);
        return true;
    }

    public boolean returnBook(Book book) {
        Objects.requireNonNull(book, "Book cannot be null");
        if (!borrowedBooks.remove(book)) {
            return false;
        }
        return book.returnBook();
    }

    public boolean hasBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }

    @Override
    public String toString() {
        return String.format("User{id='%s', name='%s', borrowedBooks=%d}",
                id, name, borrowedBooks.size());
    }
}
