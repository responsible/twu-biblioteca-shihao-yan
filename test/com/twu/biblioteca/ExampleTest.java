package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import com.twu.biblioteca.TestHelper.*;

import static org.junit.Assert.assertEquals;

public class ExampleTest {
    private final OutputStream outputStream = new ByteArrayOutputStream();
    private final OutputStream errStream = new ByteArrayOutputStream();
    private BibliotecaApp bibliotecaApp = new BibliotecaApp();
    private final String WELCOME_MSG = "Welcome to Biblioteca Public Library!\n";
    private final String[] BOOKS_NAME = {"Book1", "Book2", "Book3"};
    private final String[] BOOKS_AUTHOR = {"Author1", "Author2", "Author3"};
    private final Integer[] BOOKS_YEAR = {2016, 2016, 2017};
    private final String BOOK_COLUMN = "Title\tAuthor\tYear\n" +
            "--------------------\n";
    private final String MAIN_MENU_HEAD = "---------------\n" +
            "Menu\n"
            + "---------------\n";
    private final String[] MAIN_MENU_ITEM = {"1. List Books", "2. Checkout Book", "3. Quit"};
    private final String MAIN_MENU_TIP = String.format("---------------\nPlease select your option (1-%d):", MAIN_MENU_ITEM.length);
    private final String MAIN_MENU_ERR_MSG = "Select a valid option!\n";
    private final String MAIN_MENU_QUIT_MSG = "Bye Bye!";
    private final String BOOK_CHECKOUT_SUCCESS = "Thank you! Enjoy the book.";
    private final String BOOK_CHECKOUT_UNSUCCESS = "That book is not available.";

    @Before
    public void setOutStream() {
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errStream));
    }

    @After
    public void resetOutStream() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testMain() {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        BibliotecaApp.main(null);
        assertEquals(WELCOME_MSG + MAIN_MENU_HEAD +
                TestHelper.generateFormattedMenuItem(MAIN_MENU_ITEM) +
                MAIN_MENU_TIP + "\n" +
                String.join("\n", BOOKS_NAME), outputStream.toString().trim());
    }

    @Test
    public void testWelcome() {
        bibliotecaApp.printWelcome();
        assertEquals(WELCOME_MSG, outputStream.toString());
    }

    @Test
    public void testListBook() {
        bibliotecaApp.listBook();
        assertEquals(String.join("\n", BOOKS_NAME), outputStream.toString().trim());
    }

    @Test
    public void testPrintBookDetails() {
        bibliotecaApp.printBookDetails();
        assertEquals(BOOK_COLUMN +
                        String.format("%s\t%s\t%s\n", BOOKS_NAME[0], BOOKS_AUTHOR[0], BOOKS_YEAR[0]) +
                        String.format("%s\t%s\t%s\n", BOOKS_NAME[1], BOOKS_AUTHOR[1], BOOKS_YEAR[1]) +
                        String.format("%s\t%s\t%s\n", BOOKS_NAME[2], BOOKS_AUTHOR[2], BOOKS_YEAR[2])
                , outputStream.toString());
    }

    @Test
    public void testMainMenu() {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_HEAD +
                TestHelper.generateFormattedMenuItem(MAIN_MENU_ITEM) +
                MAIN_MENU_TIP + "\n" +
                String.join("\n", BOOKS_NAME), outputStream.toString().trim());
    }

    @Test
    public void testMainMenuWithInvalidOption() {
        System.setIn(new ByteArrayInputStream("0 1".getBytes()));
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_HEAD +
                TestHelper.generateFormattedMenuItem(MAIN_MENU_ITEM) +
                MAIN_MENU_TIP + "\n" +
                MAIN_MENU_ERR_MSG +
                String.join("\n", BOOKS_NAME), outputStream.toString().trim());
    }

    @Test
    public void testMainMenuUntilQuit() {
        System.setIn(new ByteArrayInputStream("1 1 3".getBytes()));
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_HEAD +
                TestHelper.generateFormattedMenuItem(MAIN_MENU_ITEM) +
                MAIN_MENU_TIP + "\n" +
                String.join("\n", BOOKS_NAME) + "\n" +
                String.join("\n", BOOKS_NAME) + "\n" +
                MAIN_MENU_QUIT_MSG, outputStream.toString().trim());
    }

    @Test
    public void testCheckoutBook() {
        bibliotecaApp.checkoutBook("Book2");
        bibliotecaApp.listBook();
        assertEquals(BOOK_CHECKOUT_SUCCESS + "\n" +
                String.join("\n", BOOKS_NAME[0], BOOKS_NAME[2]), outputStream.toString().trim());
    }

    @Test
    public void testCheckoutBookSuccessful() {
        System.setIn(new ByteArrayInputStream("2 Book2".getBytes()));
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_HEAD +
                TestHelper.generateFormattedMenuItem(MAIN_MENU_ITEM) +
                MAIN_MENU_TIP + "\n" +
                BOOK_CHECKOUT_SUCCESS, outputStream.toString().trim());
    }

    @Test
    public void testCheckoutBookUnsuccessful() {
        System.setIn(new ByteArrayInputStream("2 Book2 2 Book2 2 Book0".getBytes()));
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_HEAD +
                TestHelper.generateFormattedMenuItem(MAIN_MENU_ITEM) +
                MAIN_MENU_TIP + "\n" +
                BOOK_CHECKOUT_SUCCESS + "\n" +
                BOOK_CHECKOUT_UNSUCCESS + "\n" +
                BOOK_CHECKOUT_UNSUCCESS, outputStream.toString().trim());
    }
}
