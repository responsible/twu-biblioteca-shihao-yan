package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import com.twu.biblioteca.TestHelper.*;

import static org.junit.Assert.assertEquals;

public class ExampleTest {
    private final OutputStream outputStream = new ByteArrayOutputStream();
    private final OutputStream errStream = new ByteArrayOutputStream();
    private BibliotecaApp bibliotecaApp = new BibliotecaApp();

    private final String WELCOME_MSG = "Welcome to Biblioteca Public Library!";

    private final String[] BOOKS_NAME = {"Book1", "Book2", "Book3"};
    private final String[] BOOKS_AUTHOR = {"Author1", "Author2", "Author3"};
    private final Integer[] BOOKS_YEAR = {2016, 2016, 2017};
    private final String BOOK_COLUMN = "Title\tAuthor\tYear\n" +
            "--------------------\n";

    private final String MAIN_MENU_HEAD = "---------------\n" +
            "Menu\n"
            + "---------------\n";
    private final String MAIN_MENU_TIP = String.format("---------------\nPlease select your option (1-%d):", TestHelper.generateFormattedMenuItem().split("\n").length);
    private final String MAIN_MENU_FULL_PROMPT = MAIN_MENU_HEAD +
            TestHelper.generateFormattedMenuItem() +
            MAIN_MENU_TIP;
    private final String MAIN_MENU_ERR_MSG = "Select a valid option!\n";
    private final String MAIN_MENU_QUIT_MSG = "Bye Bye!";

    private final String BOOK_CHECKOUT_SUCCESS = "Thank you! Enjoy the book.";
    private final String BOOK_CHECKOUT_UNSUCCESS = "That book is not available.";
    private final String BOOK_RETURN_SUCCESS = "Thank you for returning the book.";
    private final String BOOK_RETURN_UNSUCCESS = "That is not a valid book to return.";

    private final String MOVIE_COLUMN = "Name\tYear\tDirector\tRating\n" +
            "----------------------------------------\n";
    private final String[] MOVIES_NAME = {"Movie1", "Movie2", "Movie3"};
    private final Integer[] MOVIES_YEAR = {2015, 2016, 2017};
    private final String[] MOVIES_DIRECTOR = {"Director1", "Director2", "Director3"};
    private final Integer[] MOVIES_RATING = {8, null, 10};
    private final String MOVIE_CHECKOUT_SUCCESS = "Thank you! Enjoy the movie.";
    private final String MOVIE_CHECKOUT_UNSUCCESS = "That movie is not available.";

    private final String USER_LIBRARY_NUMBER = "001-1000";
    private final String USER_PASSWORD = "123123";
    private final String USER_LIBRARY_NUMBER_INPUT_PROMPT = "Login:\nPlease input your library number:";
    private final String USER_PASSWORD_INPUT_PROMPT = "Please input your password:";
    private final String USER_NAME = "user1";
    private final String USER_EMAIL = "user1@thoughtworks.com";
    private final String USER_PHONE = "123456789";

    @Before
    public void setOutStream() {
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errStream));
    }

    @Before
    public void cleanUserLoginStatus() {
        UserManager.cleanUser();
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

    public String getFullMainMenuPrompt() {
        return MAIN_MENU_HEAD +
                TestHelper.generateFormattedMenuItem() +
                MAIN_MENU_TIP;
    }

    @Test
    public void testMain() {
        mockUserInput("6");
        BibliotecaApp.main(null);
        assertEquals(WELCOME_MSG + "\n" + getFullMainMenuPrompt() + "\n" + MAIN_MENU_QUIT_MSG
                , getOutput());
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
                        String.format("%s\t%s\t%s", BOOKS_NAME[2], BOOKS_AUTHOR[2], BOOKS_YEAR[2])
                , getOutput());
    }

    @Test
    public void testMainMenu() {
        mockUserInput("1");
        bibliotecaApp.printMainMenu();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                String.join("\n", BOOKS_NAME), getOutput());
    }

    @Test
    public void testMainMenuWithInvalidOption() {
        mockUserInput("0 1");
        bibliotecaApp.printMainMenu();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                MAIN_MENU_ERR_MSG +
                String.join("\n", BOOKS_NAME), getOutput());
    }

    @Test
    public void testMainMenuUntilQuit() {
        mockUserInput("1 1 6");
        bibliotecaApp.printMainMenu();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                String.join("\n", BOOKS_NAME) + "\n" +
                String.join("\n", BOOKS_NAME) + "\n" +
                MAIN_MENU_QUIT_MSG, getOutput());
    }

    @Test
    public void testCheckoutBook() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        bibliotecaApp.checkoutBook("Book2");
        bibliotecaApp.listBook();
        assertEquals(BOOK_CHECKOUT_SUCCESS + "\n" +
                String.join("\n", BOOKS_NAME[0], BOOKS_NAME[2]), getOutput());
    }

    @Test
    public void testCheckoutBookSuccessful() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        mockUserInput("2 Book2");
        bibliotecaApp.printMainMenu();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                BOOK_CHECKOUT_SUCCESS, getOutput());
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
    }

    @Test
    public void testReturnBook() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        mockUserInput("2 Book2");
        bibliotecaApp.printMainMenu();
        bibliotecaApp.returnBook("Book2");
        bibliotecaApp.listBook();
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
    public void testListMovies() {
        mockUserInput("4");
        bibliotecaApp.listMovie();
        assertEquals(MOVIE_COLUMN +
                        String.format("%s\t%d\t%s\t%s\n", MOVIES_NAME[0], MOVIES_YEAR[0], MOVIES_DIRECTOR[0], MOVIES_RATING[0]) +
                        String.format("%s\t%d\t%s\t%s\n", MOVIES_NAME[1], MOVIES_YEAR[1], MOVIES_DIRECTOR[1], MOVIES_RATING[1] == null ? "unrated" : null) +
                        String.format("%s\t%d\t%s\t%s", MOVIES_NAME[2], MOVIES_YEAR[2], MOVIES_DIRECTOR[2], MOVIES_RATING[2]),
                getOutput());
    }

    @Test
    public void testCheckoutMovie() {
        bibliotecaApp.checkoutMovie("Movie2");
        bibliotecaApp.listMovie();
        assertEquals(MOVIE_CHECKOUT_SUCCESS + "\n" +
                MOVIE_COLUMN +
                String.format("%s\t%d\t%s\t%s\n", MOVIES_NAME[0], MOVIES_YEAR[0], MOVIES_DIRECTOR[0], MOVIES_RATING[0]) +
                String.format("%s\t%d\t%s\t%s", MOVIES_NAME[2], MOVIES_YEAR[2], MOVIES_DIRECTOR[2], MOVIES_RATING[2]), getOutput());
    }

    @Test
    public void testUserAuthSuccess() {
        assertEquals(new User("001-1000"), UserManager.auth("001-1000", "123123"));
    }

    @Test
    public void testUserAuthFail() {
        assertEquals(null, UserManager.auth("001-1000", "123111"));
        assertEquals(null, UserManager.auth("123-1234", "123111"));
    }

    @Test
    public void testCheckoutLoginProcess() {
        mockUserInput("001-1000 123123");
        bibliotecaApp.checkoutBook("Book2");
        assertEquals(USER_LIBRARY_NUMBER_INPUT_PROMPT + "\n" +
                        USER_PASSWORD_INPUT_PROMPT + "\n" +
                        BOOK_CHECKOUT_SUCCESS,
                getOutput());
    }

    @Test
    public void testUserInformation() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        mockUserInput("5");
        bibliotecaApp.printMainMenu();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                String.format("name: %s\nemail: %s\nphone: %s", USER_NAME, USER_EMAIL, USER_PHONE), getOutput());
    }

    @Test
    public void testLoginedMainMenu() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        mockUserInput("6");
        bibliotecaApp.printMainMenu();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                MAIN_MENU_QUIT_MSG, getOutput());
    }
}
