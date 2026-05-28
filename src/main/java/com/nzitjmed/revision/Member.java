package com.nzitjmed.revision;

import java.util.ArrayList;
import java.util.List;

// Member class representing a library member
public class Member {
   private int id;
   private String name;
   private List<Book> borrowedBooks;

   //Constructor
    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getter and Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    // Add a borrowed book
    public void borrowBook(Book book) {
        this.borrowedBooks.add(book);
    }
    // Remove a returned book
    public void returnBook(Book book) {
        this.borrowedBooks.add(book);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", borrowedBooks=" + borrowedBooks.size() +
                '}';
    }


}
