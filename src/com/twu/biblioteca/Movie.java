package com.twu.biblioteca;

import java.util.Arrays;

/**
 * Created by responsible on 17-7-25.
 */
public class Movie {
    private static Movie[] movies = {new Movie("Movie1", 2015, "Director1", 8),
            new Movie("Movie2", 2016, "Director2"),
            new Movie("Movie3", 2017, "Director3", 10)};

    private String name;
    private Integer year;
    private String director;
    private Integer rating;
    private Status status;


    public enum Status {
        available, checkedout;
    }

    public Movie(String name) {
        this(name, null, null, null);
    }

    public Movie(String name, Integer year, String director, Integer rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.status = Status.available;
    }

    public Movie(String name, Integer year, String director) {
        this(name, year, director, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return name != null ? name.equals(movie.name) : movie.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public static void listAll() {
        String MOVIE_COLUMN = "Name\tYear\tDirector\tRating\n" +
                "----------------------------------------";
        System.out.println(MOVIE_COLUMN);
        for (Movie movie : movies) {
            if (movie.getStatus() == Movie.Status.available)
                System.out.println(String.format("%s\t%d\t%s\t%s", movie.getName(), movie.getYear(), movie.getDirector(), movie.getRating() == null ? "unrated" : movie.getRating()));
        }
    }

    public void checkout() {
        String MOVIE_CHECKOUT_SUCCESS = "Thank you! Enjoy the movie.";
        String MOVIE_CHECKOUT_UNSUCCESS = "That movie is not available.";

        int movieId = Arrays.asList(movies).indexOf(this);
        if (movieId != -1 && movies[movieId].getStatus() == Movie.Status.available) {
            movies[movieId].setStatus(Movie.Status.checkedout);
            System.out.println(MOVIE_CHECKOUT_SUCCESS);
        } else {
            System.out.println(MOVIE_CHECKOUT_UNSUCCESS);
        }
    }
}
