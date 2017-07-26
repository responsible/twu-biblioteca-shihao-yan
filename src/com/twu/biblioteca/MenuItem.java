package com.twu.biblioteca;

/**
 * Created by responsible on 17-7-26.
 */
public class MenuItem {
    private String title;
    private Visibility visibility;

    public String getTitle() {
        return title;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public enum Visibility {
        normal, loginRequired, guestRequired
    }

    MenuItem(String title) {
        this(title, Visibility.normal);
    }

    MenuItem(String title, Visibility visibility) {
        this.title = title;
        this.visibility = visibility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem menuItem = (MenuItem) o;

        return title.equals(menuItem.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
