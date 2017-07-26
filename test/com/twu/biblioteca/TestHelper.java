package com.twu.biblioteca;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.TestData.*;

/**
 * Created by responsible on 17-7-24.
 */
public class TestHelper {
    private static OutputStream outputStream = new ByteArrayOutputStream();
    private static OutputStream errStream = new ByteArrayOutputStream();

    private static void setOutStream() {
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errStream));
    }

    private static void cleanUserLoginStatus() {
        UserManager.cleanUser();
    }

    private static void resetOutStream() {
        System.setOut(null);
        System.setErr(null);
    }

    public static void beforeSub() {
        outputStream = new ByteArrayOutputStream();
        setOutStream();
        cleanUserLoginStatus();
    }

    public static void afterSub() {
        resetOutStream();
    }

    public static String getOutput() {
        return outputStream.toString().trim();
    }

    public static String generateFormattedMenuItem() {
        String[] MAIN_MENU_ITEM_LOGINED = {"1. List Books", "2. Checkout Book", "3. Return Book", "4. List Movies", "5. Show user information", "6. Quit"};
        String[] MAIN_MENU_ITEM_GUEST = {"1. List Books", "2. Checkout Book", "3. Return Book", "4. List Movies", "5. Login", "6. Quit"};

        String[] menuItems = UserManager.getLoginedUser() == null ? MAIN_MENU_ITEM_GUEST : MAIN_MENU_ITEM_LOGINED;
        StringBuffer stringBuffer = new StringBuffer();
        for (String item : menuItems) {
            stringBuffer.append(item + "\n");
        }
        return stringBuffer.toString();
    }

    public static void mockUserInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static String getFullMainMenuPrompt() {
        return MAIN_MENU_HEAD +
                TestHelper.generateFormattedMenuItem() +
                MAIN_MENU_TIP;
    }
}
