package com.nzitjmed.revision;

public class LibraryApp {
    public static void main(String[] args) {
        //Create a library instance
        Library library = new Library();
        //Add some books
        library.addBook(new Book(1,"The Great Gatsby","F.Scoot Fitzgerald","Classic"));
        library.addBook(new Book(2,"To kill a Mockingbird","Harper Lee","Classic"));
        library.addBook(new Book(3,"1984","George Orwell","Dystopian"));
        library.addBook(new Book(4,"Brave New World","Jean Nzit","Dystopian"));

        //Register some members
        library.registerMember(new Member(1,"Jean"));
        library.registerMember(new Member(2,"Anna"));

        // Demonstrate borrowing and returning books
        try{
            library.borrowBook(1,1); // Jean borrows "the Great Gatsby"
            library.borrowBook(2,3); // Anna borrows !1984"
            library.borrowBook(1,1); // Jean returns " The Great Gatsby"
        }catch (BookNotAvailableException | MemberNotFoundException e){
            System.out.println(e.getMessage());
        }

        // Search books by title
        System.out.println("\nSearch results for 'Great': ");
        library.searchBooksByTitle("Great").forEach(System.out:: println);

        // Get available books by genre
        System.out.println("nAvailable Dystopian Books:");
        library.getAvailableBooksByGenre("Dystopian").forEach(System.out::println);

        // Get the most borrowed books
        System.out.println("\nMost Borrowed Books");
        library.getMostBorrowedBooks().forEach(System.out::println);

    }
}
