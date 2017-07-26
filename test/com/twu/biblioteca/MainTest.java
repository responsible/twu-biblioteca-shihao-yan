package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.TestData.*;

import static com.twu.biblioteca.TestHelper.*;
import static org.junit.Assert.assertEquals;

public class MainTest {
    @Before
    public void before() {
        beforeSub();
    }

    @After
    public void after() {
        afterSub();
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
}
