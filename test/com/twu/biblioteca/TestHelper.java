package com.twu.biblioteca;

/**
 * Created by responsible on 17-7-24.
 */
public class TestHelper {
    public static String generateFormattedMenuItem(String[] menuItems) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String item : menuItems) {
            stringBuffer.append(item + "\n");
        }
        return stringBuffer.toString();
    }
}
