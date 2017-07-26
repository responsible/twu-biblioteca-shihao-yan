package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by responsible on 17-7-26.
 */
public class Menu {
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void printMenu() {
        String MAIN_MENU_HEAD = "---------------\nMenu\n---------------";
        String MAIN_MENU_TIP = String.format("---------------\nPlease select your option (1-%d):", calcMenuItemCount());
        System.out.println(MAIN_MENU_HEAD);

        boolean isLogined = UserManager.getLoginedUser() != null;
        List<MenuItem> loginRelatedMenuItem = new ArrayList<MenuItem>();
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getVisibility() == MenuItem.Visibility.normal)
                loginRelatedMenuItem.add(menuItem);
            else if (isLogined && menuItem.getVisibility() == MenuItem.Visibility.loginRequired)
                loginRelatedMenuItem.add(menuItem);
            else if (!isLogined && menuItem.getVisibility() == MenuItem.Visibility.guestRequired)
                loginRelatedMenuItem.add(menuItem);
        }
        for (MenuItem menuItem : loginRelatedMenuItem) {
            System.out.printf("%d. %s\n", loginRelatedMenuItem.indexOf(menuItem) + 1, menuItem.getTitle());
        }

        System.out.println(MAIN_MENU_TIP);
    }

    private int calcMenuItemCount() {
        int normalCnt = 0;
        int loginRequiredCnt = 0;
        int guestRequiredCnt = 0;
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getVisibility() == MenuItem.Visibility.normal) normalCnt++;
            else if (menuItem.getVisibility() == MenuItem.Visibility.loginRequired) loginRequiredCnt++;
            else if (menuItem.getVisibility() == MenuItem.Visibility.guestRequired) guestRequiredCnt++;
        }
        if (UserManager.getLoginedUser() == null)
            return normalCnt + guestRequiredCnt;
        else
            return normalCnt + loginRequiredCnt;
    }
}
