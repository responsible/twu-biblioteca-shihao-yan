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
    private final String[] MAIN_MENU_ITEM = {"1. List Books", "2. Checkout Book", "3. Return Book", "4. Quit"};
    private final String MAIN_MENU_TIP = String.format("---------------\nPlease select your option (1-%d):", MAIN_MENU_ITEM.length);
    private final String MAIN_MENU_FULL_PROMPT = MAIN_MENU_HEAD +
            TestHelper.generateFormattedMenuItem(MAIN_MENU_ITEM) +
            MAIN_MENU_TIP;
    private final String MAIN_MENU_ERR_MSG = "Select a valid option!\n";
    private final String MAIN_MENU_QUIT_MSG = "Bye Bye!";

    private final String BOOK_CHECKOUT_SUCCESS = "Thank you! Enjoy the book.";
    private final String BOOK_CHECKOUT_UNSUCCESS = "That book is not available.";
    private final String BOOK_RETURN_SUCCESS = "Thank you for returning the book.";
    private final String BOOK_RETURN_UNSUCCESS = "That is not a valid book to return.";

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

    private void mockUserInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private String getOutput() {
        return outputStream.toString().trim();
    }

    @Test
    public void testMain() {
        mockUserInput("1");
        BibliotecaApp.main(null);
        assertEquals(WELCOME_MSG + MAIN_MENU_FULL_PROMPT + "\n" +
                String.join("\n", BOOKS_NAME), getOutput());
    }

    @Test
    public void testWelcome() {
        bibliotecaApp.printWelcome();
        assertEquals(WELCOME_MSG, getOutput());
    }

    @Test
    public void testListBook() {
        bibliotecaApp.listBook();
        assertEquals(String.join("\n", BOOKS_NAME), getOutput());
    }

    @Test
    public void testPrintBookDetails() {
        bibliotecaApp.printBookDetails();
        assertEquals(BOOK_COLUMN +
                        String.format("%s\t%s\t%s\n", BOOKS_NAME[0], BOOKS_AUTHOR[0], BOOKS_YEAR[0]) +
                        String.format("%s\t%s\t%s\n", BOOKS_NAME[1], BOOKS_AUTHOR[1], BOOKS_YEAR[1]) +
                        String.format("%s\t%s\t%s\n", BOOKS_NAME[2], BOOKS_AUTHOR[2], BOOKS_YEAR[2])
                , getOutput());
    }

    @Test
    public void testMainMenu() {
        mockUserInput("1");
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_FULL_PROMPT + "\n" +
                String.join("\n", BOOKS_NAME), getOutput());
    }

    @Test
    public void testMainMenuWithInvalidOption() {
        mockUserInput("0 1");
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_FULL_PROMPT + "\n" +
                MAIN_MENU_ERR_MSG +
                String.join("\n", BOOKS_NAME), getOutput());
    }

    @Test
    public void testMainMenuUntilQuit() {
        mockUserInput("1 1 4");
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_FULL_PROMPT + "\n" +
                String.join("\n", BOOKS_NAME) + "\n" +
                String.join("\n", BOOKS_NAME) + "\n" +
                MAIN_MENU_QUIT_MSG, getOutput());
    }

    @Test
    public void testCheckoutBook() {
        bibliotecaApp.checkoutBook("Book2");
        bibliotecaApp.listBook();
        assertEquals(BOOK_CHECKOUT_SUCCESS + "\n" +
                String.join("\n", BOOKS_NAME[0], BOOKS_NAME[2]), getOutput());
    }

    @Test
    public void testCheckoutBookSuccessful() {
        mockUserInput("2 Book2");
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_FULL_PROMPT + "\n" +
                BOOK_CHECKOUT_SUCCESS, getOutput());
    }

    @Test
    public void testCheckoutBookUnsuccessful() {
        mockUserInput("2 Book2 2 Book2 2 Book0");
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_FULL_PROMPT + "\n" +
                BOOK_CHECKOUT_SUCCESS + "\n" +
                BOOK_CHECKOUT_UNSUCCESS + "\n" +
                BOOK_CHECKOUT_UNSUCCESS, getOutput());
    }

    @Test
    public void testReturnBook() {
        mockUserInput("2 Book2");
        bibliotecaApp.printMainMenu();
        bibliotecaApp.returnBook("Book2");
        bibliotecaApp.listBook();
        assertEquals(MAIN_MENU_FULL_PROMPT + "\n" +
                BOOK_CHECKOUT_SUCCESS + "\n" +
                BOOK_RETURN_SUCCESS + "\n" +
                String.join("\n", BOOKS_NAME), getOutput());
    }

    @Test
    public void testReturnBookSuccessful() {
        mockUserInput("2 Book2 3 Book2");
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_FULL_PROMPT + "\n" +
                BOOK_CHECKOUT_SUCCESS + "\n" +
                BOOK_RETURN_SUCCESS, getOutput());
    }

    @Test
    public void testReturnBookUnsuccessful() {
        mockUserInput("3 Book2 3 Book0");
        bibliotecaApp.printMainMenu();
        assertEquals(MAIN_MENU_FULL_PROMPT + "\n" +
                BOOK_RETURN_UNSUCCESS + "\n" +
                BOOK_RETURN_UNSUCCESS, getOutput());
    }
}
