package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.TestData.*;
import static com.twu.biblioteca.TestHelper.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by responsible on 17-7-26.
 */
public class BookTest {
    @Before
    public void before() {
        beforeSub();
    }

    @After
    public void after() {
        afterSub();
    }

    @Test
    public void testListBook() {
        Book.listBook();
        assertEquals(String.join("\n", BOOKS_NAME), getOutput());
    }

    @Test
    public void testPrintBookDetails() {
        Book.printAllBookDetails();
        assertEquals(BOOK_COLUMN +
                        String.format("%s\t%s\t%s\n", BOOKS_NAME[0], BOOKS_AUTHOR[0], BOOKS_YEAR[0]) +
                        String.format("%s\t%s\t%s\n", BOOKS_NAME[1], BOOKS_AUTHOR[1], BOOKS_YEAR[1]) +
                        String.format("%s\t%s\t%s", BOOKS_NAME[2], BOOKS_AUTHOR[2], BOOKS_YEAR[2])
                , getOutput());
    }

    @Test
    public void testCheckoutBook() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        new Book("Book2").checkoutBook();
        Book.listBook();
        assertEquals(BOOK_CHECKOUT_SUCCESS + "\n" +
                String.join("\n", BOOKS_NAME[0], BOOKS_NAME[2]), getOutput());
        new Book("Book2").returnBook();
    }

    @Test
    public void testCheckoutBookSuccessful() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        mockUserInput("2 Book2");
        bibliotecaApp.printMainMenu();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                BOOK_CHECKOUT_SUCCESS, getOutput());
        new Book("Book2").returnBook();
    }

    @Test
    public void testCheckoutBookUnsuccessful() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        mockUserInput("2 Book2 2 Book2 2 Book0");
        bibliotecaApp.printMainMenu();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                BOOK_CHECKOUT_SUCCESS + "\n" +
                BOOK_CHECKOUT_UNSUCCESS + "\n" +
                BOOK_CHECKOUT_UNSUCCESS, getOutput());
        new Book("Book2").returnBook();
    }

    @Test
    public void testReturnBook() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        mockUserInput("2 Book2");
        bibliotecaApp.printMainMenu();
        new Book("Book2").returnBook();
        Book.listBook();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                BOOK_CHECKOUT_SUCCESS + "\n" +
                BOOK_RETURN_SUCCESS + "\n" +
                String.join("\n", BOOKS_NAME), getOutput());
    }

    @Test
    public void testReturnBookSuccessful() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        mockUserInput("2 Book2 3 Book2");
        bibliotecaApp.printMainMenu();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                BOOK_CHECKOUT_SUCCESS + "\n" +
                BOOK_RETURN_SUCCESS, getOutput());
    }

    @Test
    public void testReturnBookUnsuccessful() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        mockUserInput("3 Book2 3 Book0");
        bibliotecaApp.printMainMenu();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                BOOK_RETURN_UNSUCCESS + "\n" +
                BOOK_RETURN_UNSUCCESS, getOutput());
    }

    @Test
    public void testBookCheckoutLoginProcess() {
        mockUserInput("001-1000 123123");
        new Book("Book2").checkoutBook();
        assertEquals(USER_LIBRARY_NUMBER_INPUT_PROMPT + "\n" +
                        USER_PASSWORD_INPUT_PROMPT + "\n" +
                        BOOK_CHECKOUT_SUCCESS,
                getOutput());
        new Book("Book2").returnBook();
    }
}
