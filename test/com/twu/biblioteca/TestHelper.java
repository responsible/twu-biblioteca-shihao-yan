package com.twu.biblioteca;

/**
 * Created by responsible on 17-7-24.
 */
public class TestHelper {
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
}
