package de.thab.cqt.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserTest {
    //Test Object : User Instance
    private User user;
    //Mock Object : Book Instance
    private Book bookMock;
    @BeforeEach
    public void setUp() {
        // Initialize the test object and the mock object before each test case
        user = new User("u1");
        bookMock = Mockito.mock(Book.class);
    }

    //Test Objective : verify that properties of user are correctly set
    @ParameterizedTest
    @ValueSource(strings = {"u1","u2","u3"})
    void TestUserProperties(String name) {
        user = new User(name);
        assertEquals(name, user.getName());
    }

    //Test Objective : verify that user can borrow book
    @Test
    void testBorrowBook() {
        when(bookMock.isCheckedOut()).thenReturn(false);
        user.borrowBook(bookMock);
        verify(bookMock).checkOut();
        assertTrue(user.getBorrowedBooks().contains(bookMock));
        assertEquals(1, user.getBorrowedBooks().size());
    }

    //Test Objective : verify that user can return book
    @Test
    void testReturnBook()
    {
        user.borrowBook(bookMock);
        user.returnBook(bookMock);
        verify(bookMock).returnBook();
        assertFalse(user.getBorrowedBooks().contains(bookMock));
        assertEquals(0, user.getBorrowedBooks().size());

    }

    //Test Objective : verify that attempting out the borrowing of already borrowed book throws exception
    @Test
    void testBorrowedBookalreadyBorrowedbook() {
        when(bookMock.isCheckedOut()).thenReturn(true);
        assertThrows(IllegalStateException.class, () -> user.borrowBook(bookMock));
    }

    //Test Objective : verify that attempting out returning non borrowed book throws exception
    @Test
    void testReturnBooknotBorrowedbook() {
        assertThrows(IllegalStateException.class, () -> user.returnBook(bookMock));

    }
}
