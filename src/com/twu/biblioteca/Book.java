package com.twu.biblioteca;

/**
 * Created by responsible on 17-7-23.
 */
public class Book {
    private String name;
    private String author;
    private Integer year;

    Book(String name) {
        this.name = name;
    }

    Book(String name, String author, Integer year) {
        this.name = name;
        this.author = author;
        this.year = year;
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
}
