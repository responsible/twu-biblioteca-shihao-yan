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
public class LoginTest {
    @Before
    public void before() {
        beforeSub();
    }

    @After
    public void after() {
        afterSub();
    }

    @Test
    public void testUserAuthSuccess() {
        assertEquals(new User(USER_LIBRARY_NUMBER), UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD));
    }

    @Test
    public void testUserAuthFail() {
        assertEquals(null, UserManager.auth("001-1000", "123111"));
        assertEquals(null, UserManager.auth("123-1234", "123111"));
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
