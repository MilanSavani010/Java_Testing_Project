package de.thab.cqt.library;


public class Book {
    private String title;
    private String author;
    private boolean isCheckedOut;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isCheckedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void checkOut() {
        if (isCheckedOut) {
            throw new IllegalStateException("Book already checked out");
        }
        isCheckedOut = true;
    }

    public void returnBook() {
        isCheckedOut = false;
    }

    public String updateTitle(String newTitle)
    {
        this.title = newTitle;
        return this.title;
    }
}
