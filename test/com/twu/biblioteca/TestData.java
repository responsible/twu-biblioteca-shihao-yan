package com.twu.biblioteca;

/**
 * Created by responsible on 17-7-26.
 */
public class TestData {
    public static final BibliotecaApp bibliotecaApp = new BibliotecaApp();

    public static final String WELCOME_MSG = "Welcome to Biblioteca Public Library!";

    public static final String[] BOOKS_NAME = {"Book1", "Book2", "Book3"};
    public static final String[] BOOKS_AUTHOR = {"Author1", "Author2", "Author3"};
    public static final Integer[] BOOKS_YEAR = {2016, 2016, 2017};
    public static final String BOOK_COLUMN = "Title\tAuthor\tYear\n" +
            "--------------------\n";

    public static final String MAIN_MENU_HEAD = "---------------\n" +
            "Menu\n"
            + "---------------\n";
    public static final String MAIN_MENU_TIP = String.format("---------------\nPlease select your option (1-%d):", TestHelper.generateFormattedMenuItem().split("\n").length);
    public static final String MAIN_MENU_FULL_PROMPT = MAIN_MENU_HEAD +
            TestHelper.generateFormattedMenuItem() +
            MAIN_MENU_TIP;
    public static final String MAIN_MENU_ERR_MSG = "Select a valid option!\n";
    public static final String MAIN_MENU_QUIT_MSG = "Bye Bye!";

    public static final String BOOK_CHECKOUT_SUCCESS = "Thank you! Enjoy the book.";
    public static final String BOOK_CHECKOUT_UNSUCCESS = "That book is not available.";
    public static final String BOOK_RETURN_SUCCESS = "Thank you for returning the book.";
    public static final String BOOK_RETURN_UNSUCCESS = "That is not a valid book to return.";

    public static final String MOVIE_COLUMN = "Name\tYear\tDirector\tRating\n" +
            "----------------------------------------\n";
    public static final String[] MOVIES_NAME = {"Movie1", "Movie2", "Movie3"};
    public static final Integer[] MOVIES_YEAR = {2015, 2016, 2017};
    public static final String[] MOVIES_DIRECTOR = {"Director1", "Director2", "Director3"};
    public static final Integer[] MOVIES_RATING = {8, null, 10};
    public static final String MOVIE_CHECKOUT_SUCCESS = "Thank you! Enjoy the movie.";
    public static final String MOVIE_CHECKOUT_UNSUCCESS = "That movie is not available.";

    public static final String USER_LIBRARY_NUMBER = "001-1000";
    public static final String USER_PASSWORD = "123123";
    public static final String USER_LIBRARY_NUMBER_INPUT_PROMPT = "Login:\nPlease input your library number:";
    public static final String USER_PASSWORD_INPUT_PROMPT = "Please input your password:";
    public static final String USER_NAME = "user1";
    public static final String USER_EMAIL = "user1@thoughtworks.com";
    public static final String USER_PHONE = "123456789";
}
