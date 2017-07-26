package com.twu.biblioteca;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class BibliotecaApp {
    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.printWelcome();
        bibliotecaApp.printMainMenu();
    }

    public void printWelcome() {
        System.out.println("Welcome to Biblioteca Public Library!");
    }

    public void printMainMenu() {
        Menu menu = new Menu();
        menu.addMenuItem(new MenuItem("List Books"));
        menu.addMenuItem(new MenuItem("Checkout Book"));
        menu.addMenuItem(new MenuItem("Return Book"));
        menu.addMenuItem(new MenuItem("List Movies"));
        menu.addMenuItem(new MenuItem("Login", MenuItem.Visibility.guestRequired));
        menu.addMenuItem(new MenuItem("Show user information", MenuItem.Visibility.loginRequired));
        menu.addMenuItem(new MenuItem("Quit"));
        menu.printMenu();

        menuChoiceHandler();
    }

    private void menuChoiceHandler() {
        Scanner input = new Scanner(System.in);
        boolean toQuit = false;
        try {
            while (!toQuit) {
                Integer userChoice = input.nextInt();
                switch (userChoice) {
                    case 1:
                        Book.listBook();
                        break;
                    case 2:
                        new Book(input.next()).checkoutBook();
                        break;
                    case 3:
                        new Book(input.next()).returnBook();
                        break;
                    case 4:
                        Movie.listAll();
                        break;
                    case 5:
                        if (UserManager.getLoginedUser() == null)
                            UserManager.getInputAndLogin();
                        else
                            UserManager.getLoginedUser().printInformation();
                        break;
                    case 6:
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
