package com.twu.biblioteca;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ExampleTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errStream = new ByteArrayOutputStream();
    private BibliotecaApp bibliotecaApp = new BibliotecaApp();
    private final String WELCOME_MSG = "Welcome to Biblioteca Public Library!\n";
    private final String[] BOOKS_NAME = {"Book1", "Book2", "Book3"};

    @Before
    public void setOutStream() {
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errStream));
    }

    @After
    public void resetOutStream() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testMain() {
        BibliotecaApp.main(null);
        assertEquals(WELCOME_MSG + String.join("\n",BOOKS_NAME), outputStream.toString().trim());
    }

    @Test
    public void testWelcome() {
        bibliotecaApp.printWelcome();
        assertEquals(WELCOME_MSG, outputStream.toString());
    }

    @Test
    public void testListBook() {
        bibliotecaApp.listBook();
        assertEquals(String.join("\n",BOOKS_NAME), outputStream.toString().trim());
    }
}
