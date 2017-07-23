package com.twu.biblioteca;

/**
 * Created by responsible on 17-7-23.
 */
public class Book {
    Book(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
