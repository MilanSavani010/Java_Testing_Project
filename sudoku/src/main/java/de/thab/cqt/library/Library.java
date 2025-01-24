package de.thab.cqt.library;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<BorrowRecord> borrowRecords;

    public Library() {
        this.books = new ArrayList<>();
        this.borrowRecords = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(User user, Book book) {
        if(!isBookAvailable(book)) {
            throw new IllegalStateException("Book is not exist in this library");
        }
        user.borrowBook(book);
        BorrowRecord record = new BorrowRecord(user, book, LocalDate.now());
        borrowRecords.add(record);
    }

    public void returnBook(User user, Book book) {
        if(!isBookAvailable(book)) {
            throw new IllegalStateException("Book does not belongs to this library");
        }
        user.returnBook(book);
        for (BorrowRecord record : borrowRecords) {
            if (record.getUser().equals(user) && record.getBook().equals(book) && record.getReturnDate() == null) {
                record.returnBook();
                break;
            }
        }
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public boolean isBookAvailable(Book book) {
        return books.contains(book);
    }

}
