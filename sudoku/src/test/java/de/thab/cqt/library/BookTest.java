package de.thab.cqt.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    // Test Object : Book instance
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("t1","au1");
    }

    // Test objectives: Verify that the book's properties (title and author) are correctly set
    @ParameterizedTest
    @CsvSource({
            "t1,au1",
            "t2,au2",
            "t3,au3"
    })
    void testBookProperties(String title, String author) {
        Book book = new Book(title, author);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
    }

    // Test objectives: Verify that the book can be checked out
    @Test
    void testCheckOut()
    {
        book.checkOut();
        assertTrue(book.isCheckedOut());
    }

    // Test objectives: Verify that the book can be returned
    @Test
    void TestReturnBook()
    {
        book.checkOut();
        book.returnBook();
        assertFalse(book.isCheckedOut());
    }

    // Test objectives: Verify that attempting to check out an already checked-out book throws an exception
    @Test
    void testCheckedoutAlreadyCheckedOutBook()
    {
        book.checkOut();
        assertThrows(IllegalStateException.class, book::checkOut);
    }
}
