package com.twu.biblioteca;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class BibliotecaApp {
    private Book[] books = {new Book("Book1", "Author1", 2016),
            new Book("Book2", "Author2", 2016),
            new Book("Book3", "Author3", 2017)};

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.printWelcome();
        bibliotecaApp.printMainMenu();
    }

    public void printWelcome() {
        System.out.println("Welcome to Biblioteca Public Library!");
    }

    public void listBook() {
        for (Book book : books) {
            System.out.println(book.getName());
        }
    }

    public void printBookDetails() {
        String BOOK_COLUMN = "Title\tAuthor\tYear\n" +
                "--------------------";
        System.out.println(BOOK_COLUMN);
        for (Book book : books) {
            System.out.printf("%s\t%s\t%s\n", book.getName(), book.getAuthor(), book.getYear());
        }
    }

    public void printMainMenu() {
        String MAIN_MENU_HEAD = "---------------\n" + "" +
                "Menu\n"
                + "---------------";
        String[] MAIN_MENU_ITEM = {"1. List Books", "2. Quit"};
        String MAIN_MENU_TIP = String.format("---------------\nPlease select your option (1-%d):", MAIN_MENU_ITEM.length);
        System.out.println(MAIN_MENU_HEAD);
        for (String item : MAIN_MENU_ITEM) {
            System.out.println(item);
        }
        System.out.println(MAIN_MENU_TIP);

        Scanner input = new Scanner(System.in);
        boolean toQuit = false;
        try {
            while (!toQuit) {
                Integer userChoice = input.nextInt();
                switch (userChoice) {
                    case 1:
                        this.listBook();
                        continue;
                    case 2:
                        System.out.println("Bye Bye!");
                        toQuit = true;
                        break;
                    default:
                        System.out.println("Select a valid option!");
                }
            }
        } catch (NoSuchElementException ex) {

        }
    }
}
