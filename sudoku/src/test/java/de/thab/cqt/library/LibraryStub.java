package de.thab.cqt.library;

import java.util.ArrayList;
import java.util.List;

public class LibraryStub extends Library{
     @Override
    public List<Book> getBooks() {
         List<Book> books = new ArrayList<Book>();
         books.add(new Book("Stub Book 1","Stub Author 1"));
         books.add(new Book("Stub Book 2","Stub Author 2"));
         return books;
     }

     @Override
    public Book findBookByTitle(String title) {
         if("Stub Book 1".equalsIgnoreCase(title))
         {
             return new Book("Stub Book 1","Stub Author 1");
         }else if("Stub Book 2".equalsIgnoreCase(title))
         {
             return new Book("Stub Book 2","Stub Author 2");
         }
         return null;
     }
}
