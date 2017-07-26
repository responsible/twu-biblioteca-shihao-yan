package com.twu.biblioteca;

import java.util.Arrays;

/**
 * Created by responsible on 17-7-23.
 */
public class Book {
    private static Book[] books = {new Book("Book1", "Author1", 2016),
            new Book("Book2", "Author2", 2016),
            new Book("Book3", "Author3", 2017)};

    public enum Status {
        available, checkedout
    }

    private String name;
    private String author;
    private Integer year;
    private Status status;

    Book(String name) {
        this.name = name;
    }

    Book(String name, String author, Integer year) {
        this(name);
        this.author = author;
        this.year = year;
        this.status = Status.available;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return name != null ? name.equals(book.name) : book.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public static void listBook() {
        for (Book book : books) {
            if (book.getStatus() == Book.Status.available) System.out.println(book.getName());
        }
    }

    public static void printAllBookDetails() {
        String BOOK_COLUMN = "Title\tAuthor\tYear\n" +
                "--------------------";
        System.out.println(BOOK_COLUMN);
        for (Book book : books) {
            System.out.printf("%s\t%s\t%s\n", book.getName(), book.getAuthor(), book.getYear());
        }
    }

    public void checkoutBook() {
        String BOOK_CHECKOUT_SUCCESS = "Thank you! Enjoy the book.";
        String BOOK_CHECKOUT_UNSUCCESS = "That book is not available.";

        User user = UserManager.getLoginedUser();
        if (user == null) {
            user = (new UserManager()).getInputAndLogin();
        }
        if (user != null) {
            Book book = findBookInstance();
            if (book == null || book.getStatus() == Book.Status.checkedout) {
                System.out.println(BOOK_CHECKOUT_UNSUCCESS);
            } else {
                book.setStatus(Book.Status.checkedout);
                System.out.println(BOOK_CHECKOUT_SUCCESS);
            }
        }
    }

    public void returnBook() {
        String BOOK_RETURN_SUCCESS = "Thank you for returning the book.";
        String BOOK_RETURN_UNSUCCESS = "That is not a valid book to return.";

        User user = UserManager.getLoginedUser();
        if (user == null) {
            user = (new UserManager()).getInputAndLogin();
        }
        if (user != null) {
            Book book = findBookInstance();
            if (book == null || book.getStatus() == Book.Status.available)
                System.out.println(BOOK_RETURN_UNSUCCESS);
            else {
                book.setStatus(Book.Status.available);
                System.out.println(BOOK_RETURN_SUCCESS);
            }
        }
    }

    private Book findBookInstance() {
        try {
            return books[Arrays.asList(books).indexOf(this)];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
}
