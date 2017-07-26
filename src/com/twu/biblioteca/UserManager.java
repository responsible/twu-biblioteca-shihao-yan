package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by responsible on 17-7-25.
 */
public class UserManager {
    private static final Map<String, User> users = new HashMap<String, User>();
    private static User loginedUser = null;

    static {
        users.put("001-1000", new User("001-1000", "123123", "user1", "user1@thoughtworks.com", "123456789"));
        users.put("001-1001", new User("001-1001", "123123"));
        users.put("001-1002", new User("001-1002", "123123"));
    }

    public static User getInputAndLogin() {
        String USER_LIBRARY_NUMBER_INPUT_PROMPT = "Login:\nPlease input your library number:";
        String USER_PASSWORD_INPUT_PROMPT = "Please input your password:";
        String USER_AUTH_FAIL = "Wrong library number or password!";

        Scanner input = new Scanner(System.in);
        System.out.println(USER_LIBRARY_NUMBER_INPUT_PROMPT);
        String libraryNumber = input.next();
        System.out.println(USER_PASSWORD_INPUT_PROMPT);
        String password = input.next();

        User user = auth(libraryNumber, password);
        if (user == null) {
            System.out.println(USER_AUTH_FAIL);
            return null;
        } else {
            return user;
        }
    }

    public static User auth(String libraryNumber, String password) {
        if (users.get(libraryNumber) != null && password.equals(users.get(libraryNumber).getPassword())) {
            loginedUser = users.get(libraryNumber);
            return loginedUser;
        } else
            return null;
    }

    public static User getLoginedUser() {
        return loginedUser;
    }

    public static void cleanUser() {
        loginedUser = null;
    }
}
