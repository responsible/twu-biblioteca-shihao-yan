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
    public void testWelcome() {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.main(null);
        assertEquals("Welcome to Biblioteca Public Library!",outputStream.toString());
    }
}
