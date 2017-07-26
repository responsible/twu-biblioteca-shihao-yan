package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.twu.biblioteca.TestHelper.*;
import static org.junit.Assert.assertEquals;
import static com.twu.biblioteca.TestData.*;

/**
 * Created by responsible on 17-7-26.
 */
public class UserInformationTest {
    @Before
    public void before() {
        beforeSub();
    }

    @After
    public void after() {
        afterSub();
    }

    @Test
    public void testUserInformation() {
        UserManager.auth(USER_LIBRARY_NUMBER, USER_PASSWORD);
        mockUserInput("5");
        bibliotecaApp.printMainMenu();
        assertEquals(getFullMainMenuPrompt() + "\n" +
                String.format("name: %s\nemail: %s\nphone: %s", USER_NAME, USER_EMAIL, USER_PHONE), getOutput());
    }
}
