package com.library.management;

import java.util.Objects;

/**
 * Represents a book in the library.
 */
public class Book {
    private final String id;
    private String title;
    private String author;
    private boolean available;

    public Book(String id, String title, String author) {
        this.id = Objects.requireNonNull(id, "Book id cannot be null");
        this.title = Objects.requireNonNull(title, "Book title cannot be null");
        this.author = Objects.requireNonNull(author, "Book author cannot be null");
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Objects.requireNonNull(title, "Book title cannot be null");
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = Objects.requireNonNull(author, "Book author cannot be null");
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean issue() {
        if (!available) {
            return false;
        }
        available = false;
        return true;
    }

    public boolean returnBook() {
        if (available) {
            return false;
        }
        available = true;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Book{id='%s', title='%s', author='%s', available=%s}",
                id, title, author, available ? "Yes" : "No");
    }
}
