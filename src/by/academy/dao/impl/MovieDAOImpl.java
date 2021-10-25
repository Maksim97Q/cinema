package by.academy.dao.impl;

import by.academy.dao.ConnectionDAO;
import by.academy.dao.MovieDAO;
import by.academy.model.Movie;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {

    List<Movie> movies = new ArrayList<>();

    @Override
    public List<Movie> readAllMovie() throws SQLException {
        Connection connection = ConnectionDAO.connection();
        PreparedStatement preparedStatement = (PreparedStatement) connection
                .prepareStatement("SELECT * FROM movie");
        ResultSet resultSet = preparedStatement.executeQuery();
        int get = 0;
        while (resultSet.next()) {
            movies.add(new Movie());
            movies.get(get).setId(resultSet.getInt("id"));
            movies.get(get).setNameMovie(resultSet.getString("name"));
            movies.get(get).setDate(LocalDate.parse(resultSet.getString("number_time")));
            movies.get(get).setListTicket(resultSet.getString("ticket_list"));
            get++;
        }
        return movies;
    }

    @Override
    public boolean updateMovie(Movie movie) throws SQLException {
        Connection connection = ConnectionDAO.connection();
        PreparedStatement stmt = (PreparedStatement) connection
                .prepareStatement("SELECT id, name, number_time FROM movie WHERE name=? AND id=?");
        stmt.setString(1, movie.getNameMovie());
        stmt.setInt(2, movie.getId());
        ResultSet a = stmt.executeQuery();
        if (!a.next()) {
            PreparedStatement stm = (PreparedStatement) connection
                    .prepareStatement("UPDATE movie SET name=?, number_time=? WHERE id=?");
            stm.setString(1, movie.getNameMovie());
            stm.setString(2, movie.getDate());
            stm.setInt(3, movie.getId());
            stm.execute();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMovie(Movie movie) throws SQLException {
        Connection connection = ConnectionDAO.connection();
        PreparedStatement stmt = (PreparedStatement) connection
                .prepareStatement("SELECT id, name FROM movie WHERE id=? AND name=?");
        stmt.setInt(1, movie.getId());
        stmt.setString(2, movie.getNameMovie());
        ResultSet a = stmt.executeQuery();
        if (a.next()) {
            PreparedStatement stm = (PreparedStatement) connection
                    .prepareStatement("DELETE FROM movie WHERE id=? AND name=?");
            stm.setInt(1, movie.getId());
            stm.setString(2, movie.getNameMovie());
            stm.execute();
            return true;
        }
        return false;
    }

    @Override
    public boolean createMovie(Movie movie) throws SQLException {
        Connection connection = ConnectionDAO.connection();
        PreparedStatement stmt = (PreparedStatement) connection
                .prepareStatement("SELECT name, number_time FROM movie WHERE name=? AND number_time=?");
        stmt.setString(1, movie.getNameMovie());
        stmt.setString(2, movie.getDate());
        ResultSet a = stmt.executeQuery();
        if (!a.next()) {
            PreparedStatement stm = (PreparedStatement) connection
                    .prepareStatement("INSERT INTO movie (name, number_time, ticket_list) VALUES (?,?,?)");
            stm.setString(1, movie.getNameMovie());
            stm.setString(2, movie.getDate());
            stm.setString(3,movie.getListTicket());
            stm.execute();
            return true;
        }
        return false;
    }
}