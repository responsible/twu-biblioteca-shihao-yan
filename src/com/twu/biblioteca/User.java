package com.twu.biblioteca;

/**
 * Created by responsible on 17-7-25.
 */
public class User {
    private String libraryNumber;
    private String password;

    public User(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return libraryNumber.equals(user.libraryNumber);
    }

    @Override
    public int hashCode() {
        return libraryNumber.hashCode();
    }
}
