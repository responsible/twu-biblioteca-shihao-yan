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
public class MovieTest {
    @Before
    public void before() {
        beforeSub();
    }

    @After
    public void after() {
        afterSub();
    }

    @Test
    public void testListMovies() {
        mockUserInput("4");
        Movie.listAll();
        assertEquals(MOVIE_COLUMN +
                        String.format("%s\t%d\t%s\t%s\n", MOVIES_NAME[0], MOVIES_YEAR[0], MOVIES_DIRECTOR[0], MOVIES_RATING[0]) +
                        String.format("%s\t%d\t%s\t%s\n", MOVIES_NAME[1], MOVIES_YEAR[1], MOVIES_DIRECTOR[1], MOVIES_RATING[1] == null ? "unrated" : null) +
                        String.format("%s\t%d\t%s\t%s", MOVIES_NAME[2], MOVIES_YEAR[2], MOVIES_DIRECTOR[2], MOVIES_RATING[2]),
                getOutput());
    }

    @Test
    public void testCheckoutMovie() {
        new Movie("Movie2").checkout();
        Movie.listAll();
        assertEquals(MOVIE_CHECKOUT_SUCCESS + "\n" +
                MOVIE_COLUMN +
                String.format("%s\t%d\t%s\t%s\n", MOVIES_NAME[0], MOVIES_YEAR[0], MOVIES_DIRECTOR[0], MOVIES_RATING[0]) +
                String.format("%s\t%d\t%s\t%s", MOVIES_NAME[2], MOVIES_YEAR[2], MOVIES_DIRECTOR[2], MOVIES_RATING[2]), getOutput());
    }
}
