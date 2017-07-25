package com.twu.biblioteca;

/**
 * Created by responsible on 17-7-25.
 */
public class Movie {
    private String name;
    private Integer year;
    private String director;
    private Integer rating;

    public Movie(String name, Integer year, String director, Integer rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
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
}
