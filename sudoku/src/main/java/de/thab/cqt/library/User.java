package de.thab.cqt.library;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (book.isCheckedOut()) {
            throw new IllegalStateException("Book already checked out");
        }
        book.checkOut();
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        if (!borrowedBooks.contains(book)) {
            throw new IllegalStateException("Book not borrowed by this user");
        }
        book.returnBook();
        borrowedBooks.remove(book);
    }
}
