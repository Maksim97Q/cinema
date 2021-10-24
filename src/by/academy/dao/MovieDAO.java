package by.academy.dao;

import by.academy.model.Movie;

import java.sql.SQLException;
import java.util.List;

public interface MovieDAO {
    List<Movie> readAllMovie() throws SQLException;
    boolean updateMovie(Movie movie) throws SQLException;
    boolean deleteMovie(Movie movie) throws SQLException;
    boolean createMovie(Movie movie) throws SQLException;
}
