package by.academy.service.impl;

import by.academy.dao.impl.MovieDAOImpl;
import by.academy.model.Movie;
import by.academy.service.MovieService;
import java.sql.SQLException;
import java.util.List;

public class MovieServiceImpl implements MovieService {
    MovieDAOImpl movieDAOImpl;

    public MovieServiceImpl(MovieDAOImpl movieDAOImpl) {
        this.movieDAOImpl = movieDAOImpl;
    }

    @Override
    public List<Movie> read(Movie movie) {
        try {
            return movieDAOImpl.readAllMovie();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Movie movie) {
        try {
           if (movieDAOImpl.updateMovie(movie)) {
               return true;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Movie movie) {
        try {
            if (movieDAOImpl.deleteMovie(movie)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(Movie movie) {
        try {
            if (movieDAOImpl.createMovie(movie)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
