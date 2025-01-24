package de.thab.cqt.library;

public class UserDriver {
    public static void main(String[] args) {
        User user = new User("Driver User");

        LibraryStub libraryStub = new LibraryStub();


        //Borrow Book
        Book book = libraryStub.findBookByTitle("Stub Book 1");
        if(book != null) {
            user.borrowBook(book);
            System.out.println("Borrowed book " + book.getTitle());
        }

        //Return the Book
        user.returnBook(book);
        System.out.println("Returned book " + book.getTitle());
    }
}
