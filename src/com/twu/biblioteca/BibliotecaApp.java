package com.twu.biblioteca;

public class BibliotecaApp {
    private Book[] books = {new Book("Book1"),
            new Book("Book2"),
            new Book("Book3")};

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.printWelcome();
        bibliotecaApp.listBook();
    }

    public void printWelcome(){
        System.out.println("Welcome to Biblioteca Public Library!");
    }


    public void listBook() {
        for (Book book : books) {
            System.out.println(book.getName());
        }
    }
}
