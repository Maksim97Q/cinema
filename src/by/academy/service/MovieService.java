package by.academy.service;

import by.academy.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> read(Movie movie);
    boolean update(Movie movie);
    boolean delete(Movie movie);
    boolean create(Movie movie);
}
