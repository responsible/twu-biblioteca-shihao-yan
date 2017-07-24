package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

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
    private final String[] MAIN_MENU_ITEM = {"1. List Books"};
    private final String MAIN_MENU_TIP = "\n---------------\nPlease select your choice (1-1):";

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
                MAIN_MENU_ITEM[0] +
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
                MAIN_MENU_ITEM[0] +
                MAIN_MENU_TIP + "\n" +
                String.join("\n", BOOKS_NAME), outputStream.toString().trim());
    }
}
