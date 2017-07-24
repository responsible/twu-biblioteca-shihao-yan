package com.twu.biblioteca;

/**
 * Created by responsible on 17-7-23.
 */
public class Book {
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
}
