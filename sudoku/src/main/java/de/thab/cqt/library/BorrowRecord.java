package de.thab.cqt.library;


import java.time.LocalDate;

public class BorrowRecord {
    private User user;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRecord(User user, Book book, LocalDate borrowDate) {
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = null;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
    }
}
