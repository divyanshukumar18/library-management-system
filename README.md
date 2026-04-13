# Library Management System

A small Java-based library management system demonstrating core OOP concepts.

## Project structure

- `src/com/library/management/Book.java` - Book entity with issue/return state.
- `src/com/library/management/User.java` - User entity with borrowed book tracking.
- `src/com/library/management/Library.java` - Manages collections of books and users.
- `src/com/library/management/LibraryManagementSystem.java` - CLI entry point with issue and return workflows.

## Build and run

From the repository root:

```bash
javac -d out src/com/library/management/*.java
java -cp out com.library.management.LibraryManagementSystem
```

## Features

- Add books and users
- Issue books to users
- Return books from users
- Display book and user lists
- Show individual user borrowing report

## Notes

The CLI includes sample data so the system can be tested immediately after launch.
