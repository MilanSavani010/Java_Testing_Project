package de.thab.cqt.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryITTest {
    //Test Objects
    private Library library;
    private User user;
    private Book book;
    private Book book1;

    @BeforeEach
    public void setUp() {

        library = new Library();
        user = new User("u1");
        book = new Book("t1","au1");
        book1 = new Book("t2","au2");
        library.addBook(book);
    }

    //Test Objective : Verify that a user can borrow a book from the library
    @Test
    void testBorrowBook() {
        library.borrowBook(user, book);
        assertTrue(book.isCheckedOut());
        assertTrue(user.getBorrowedBooks().contains(book));
        BorrowRecord borrowRecord = library.getBorrowRecords().stream()
                .filter(record -> record.getUser().equals(user) && record.getBook()
                        .equals(book)).findFirst().orElse(null);
        assertNotNull(borrowRecord);
        assertNull(borrowRecord.getReturnDate());

    }
    //Test Objective : verify that a user can return a borrowed book to the library
    @Test
    void testReturnBook() {
        library.borrowBook(user, book);
        library.returnBook(user, book);
        assertFalse(user.getBorrowedBooks().contains(book));
        assertFalse(book.isCheckedOut());
        BorrowRecord borrowRecord = library.getBorrowRecords().stream()
                .filter(record -> record.getUser().equals(user) && record.getBook()
                        .equals(book)).findFirst().orElse(null);
        assertNotNull(borrowRecord);
        assertNotNull(borrowRecord.getReturnDate());

    }
    //Test Objective : verify that attempting to borrow already checked out book through exception
    //Test Objective : verify that attempting to return a book not borrowed by user throws an exception

}
