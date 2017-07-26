package com.twu.biblioteca;

/**
 * Created by responsible on 17-7-25.
 */
public class User {
    private String libraryNumber;
    private String password;
    private String name;
    private String email;
    private String phone;

    public User(String libraryNumber) {
        this(libraryNumber, null, null, null, null);
    }

    public User(String libraryNumber, String password) {
        this(libraryNumber, password, null, null, null);
    }

    public User(String libraryNumber, String password, String name, String email, String phone) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void printInformation() {
        System.out.println(String.format("name: %s\nemail: %s\nphone: %s", name, email, phone));
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
