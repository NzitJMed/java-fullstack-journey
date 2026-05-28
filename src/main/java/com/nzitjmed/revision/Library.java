package com.nzitjmed.revision;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Map<Integer, Book> books; // Stores bboks by ID
    private Map<Integer, Member> members; // Stores members by ID

    //Constructor
    public Library() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
    }

    //Add a book to the library
    public void addBook(Book book) {
        books.put(book.getId(), book);
        System.out.println("Book added: " + book.getTitle());
    }

    //Register a new member
    public void registerMember(Member member) {
        members.put(member.getId(), member);
        System.out.println("Member registered: " + member.getName());
    }


    //Borrow a book by memberId and bookId
    public void borrowBook(int memberId, int bookId) throws BookNotAvailableException, MemberNotFoundException
    {
        // Find the member using optional
        Member member = Optional.ofNullable(members.get(memberId))
                .orElseThrow(() -> new MemberNotFoundException("Member with ID " + memberId + " not found."));


    //Find the book using optional
    Book book = Optional.ofNullable(books.get(bookId))
            .orElseThrow(() -> new BookNotAvailableException("Book with ID" + bookId + "not found"));
        if(!book.isAvailable()){

            throw new BookNotAvailableException("Book '" + book.getTitle() + "' is not available");
    }
    // Mark the book as unavailable and add it to the member's borrowed list
         book.setAvailable(false);
        member.borrowBook(book);
        System.out.println(member.getName()+" has borrowed the book: "+book.getTitle());
    }


    // Return a book memberId and bookId
    public void returnBook(int memberId,int bookId) throws BookNotAvailableException,MemberNotFoundException
    {
        //Find the member using Optional
        Member member = Optional.ofNullable(members.get(memberId))
                .orElseThrow(() -> new MemberNotFoundException("Member with ID " + memberId + " not found."));


        //Find the book using Optional
        Book book = Optional.ofNullable(books.get(bookId))
                .orElseThrow(() -> new BookNotAvailableException("Book with ID" + bookId + "not found"));
        if (book.isAvailable()) {
            throw new BookNotAvailableException("Book '" + book.getTitle() + "' was not available");
        }


        //Mark the book as available and remove it from the member's borrowed list
        book.setAvailable(true);
        member.returnBook(book);
        System.out.println(member.getName() + " has returned the book: " + book.getTitle());
    }

    // Search books by title (case-insensitive)
    public List<Book>searchBooksByTitle(String keyword)
    {
        return books.values().stream()
                .filter(book ->book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        }


    //Get available books by genre
    public List<Book> getAvailableBooksByGenre(String genre )
    {
        return books.values().stream()
                .filter(book ->book.getGenre().equalsIgnoreCase(genre) && book.isAvailable())
                .collect(Collectors.toList());
    }




    // Get the most borrowed books
    public List<Book>getMostBorrowedBooks()
    {
        return members.values().stream()
                .flatMap(member ->member.getBorrowedBooks().stream())
                .collect(Collectors.groupingBy(Book::getTitle,Collectors.counting()))
                .entrySet().stream()
                .sorted((e1,e2) ->e2.getValue().compareTo(e1.getValue()))
                .map(entry ->books.values().stream()
                        .filter(book ->book.getTitle().equals(entry.getKey()))
                        .findFirst()
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());



    }


}
