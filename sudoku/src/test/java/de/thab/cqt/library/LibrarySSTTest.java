package de.thab.cqt.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarySSTTest {
    //Test Objects
    private Library library;
    private User user1;
    private User user2;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        library = new Library();
        user1 = new User("John Doe");
        user2 = new User("Jane Smith");
        book1 = new Book("b1","au1");
        book2 = new Book("b2","au2");
        library.addBook(book1);
        library.addBook(book2);
    }

    //System test : Verify the entire flow of a user borrowing and returning a book
    @Test
    public void testBorrowAndReturnBookFlow() {
        library.borrowBook(user1, book1);
        assertTrue(book1.isCheckedOut());
        assertTrue(user1.getBorrowedBooks().contains(book1));
        BorrowRecord borrowRecord = library.getBorrowRecords().stream()
                .filter(record -> record.getUser().equals(user1) && record.getBook().equals(book1))
                .findFirst().orElse(null);
        assertNotNull(borrowRecord);
        assertEquals(LocalDate.now(), borrowRecord.getBorrowDate());
        assertNull(borrowRecord.getReturnDate());

        library.returnBook(user1, book1);
        assertFalse(book1.isCheckedOut());
        assertFalse(user1.getBorrowedBooks().contains(book1));
         borrowRecord = library.getBorrowRecords().stream()
                .filter(record -> record.getUser().equals(user1) && record.getBook().equals(book1))
                .findFirst().orElse(null);
        assertNotNull(borrowRecord);
        assertNotNull(borrowRecord.getReturnDate());
        assertEquals(LocalDate.now(), borrowRecord.getReturnDate());

    }

    //System test : Verify the entire flow of a user borrowing and returning(false book) a book
    @Test
    public void testBorrowAndFalseReturnBookFaultFlow() {
        library.borrowBook(user1, book1);
        assertTrue(book1.isCheckedOut());
        assertTrue(user1.getBorrowedBooks().contains(book1));
        BorrowRecord borrowRecord = library.getBorrowRecords().stream()
                .filter(record -> record.getUser().equals(user1) && record.getBook().equals(book1))
                .findFirst().orElse(null);
        assertNotNull(borrowRecord);
        assertEquals(LocalDate.now(), borrowRecord.getBorrowDate());
        assertNull(borrowRecord.getReturnDate());

        assertThrows(IllegalStateException.class,() -> library.returnBook(user1, book2));
        assertFalse(book2.isCheckedOut());
        assertFalse(user1.getBorrowedBooks().contains(book2));
        borrowRecord = library.getBorrowRecords().stream()
                .filter(record -> record.getUser().equals(user1) && record.getBook().equals(book2))
                .findFirst().orElse(null);
        assertNull(borrowRecord);

    }

    //System test : Verify the entire flow of a user borrowing already checked out a book
    @Test
    public void testFalseBorrowBookFaultFlow() {
        library.borrowBook(user1, book1);
        assertTrue(book1.isCheckedOut());
        assertTrue(user1.getBorrowedBooks().contains(book1));
        BorrowRecord borrowRecord = library.getBorrowRecords().stream()
                .filter(record -> record.getUser().equals(user1) && record.getBook().equals(book1))
                .findFirst().orElse(null);
        assertNotNull(borrowRecord);
        assertEquals(LocalDate.now(), borrowRecord.getBorrowDate());
        assertNull(borrowRecord.getReturnDate());

        assertThrows(IllegalStateException.class,() -> library.borrowBook(user1, book1));


    }
}
