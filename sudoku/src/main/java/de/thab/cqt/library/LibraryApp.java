package de.thab.cqt.library;


public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        Book book1 = new Book("1984", "George Orwell");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee");
        User user = new User("John Doe");
        library.addBook(book1);
        library.addBook(book2);
        library.borrowBook(user, book1);
        System.out.println("Library books:");
        for (Book book : library.getBooks()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " - " + (book.isCheckedOut() ? "Checked out" : "Available"));
        }
        library.returnBook(user, book1);
        System.out.println("\nAfter returning the book:");
        for (Book book : library.getBooks()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " - " + (book.isCheckedOut() ? "Checked out" : "Available"));
        }
    }
}
