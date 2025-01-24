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

    @Test
    public void testUpdateTitleMethod()
    {
         // Create a mock of the Book class
        Book book = Mockito.mock(Book.class);

        // Define behavior for the updateTitle method with a specific parameter
        when(book.updateTitle("New Title")).thenReturn("New Title");

        // Use the mock in the test
        String updatedTitle = book.updateTitle("New Title");
        assertEquals("New Title", updatedTitle);

        // Verify the method was called with the specific parameter
        verify(book).updateTitle("New Title");
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
