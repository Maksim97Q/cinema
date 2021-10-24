package by.academy.dao.impl;

import by.academy.dao.ConnectionDAO;
import by.academy.dao.Password;
import by.academy.dao.UserDAO;
import by.academy.model.Movie;
import by.academy.model.User;
import by.academy.model.Users;
import com.mysql.jdbc.PreparedStatement;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    Password password;

    public UserDAOImpl(Password password) {
        this.password = password;
    }

    @Override
    public boolean createUser(User user) throws SQLException {
        Connection connection = ConnectionDAO.connection();
        PreparedStatement stmt = (PreparedStatement) connection
                .prepareStatement("SELECT id, login, password, user_level FROM user WHERE login=? AND password=?");
        stmt.setString(1, user.getLogin());
        stmt.setString(2, String.valueOf(password.password(user)));
        ResultSet a = stmt.executeQuery();
        if (!a.next()) {
            PreparedStatement stm = (PreparedStatement) connection
                    .prepareStatement("INSERT INTO user (login, password, user_level) VALUES (?,?,?)");
            stm.setString(1, user.getLogin());
            stm.setString(2, String.valueOf(password.password(user)));
            stm.setString(3, "user");
            stm.execute();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) throws SQLException {
        Connection connection = ConnectionDAO.connection();
        PreparedStatement stmt = (PreparedStatement) connection
                .prepareStatement("SELECT id, login, password FROM user WHERE login=?");
        stmt.setString(1, user.getLogin());
        ResultSet a = stmt.executeQuery();
        if (a.next()) {
            PreparedStatement stm = (PreparedStatement) connection
                    .prepareStatement("DELETE FROM user WHERE login=? AND id=?");
            stm.setString(1, user.getLogin());
            stm.setInt(2, user.getId());
            stm.execute();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        Connection connection = ConnectionDAO.connection();
        PreparedStatement stmt = (PreparedStatement) connection
                .prepareStatement("SELECT id, login, password FROM user WHERE id=? AND login=? AND password=?");
        stmt.setInt(1, user.getId());
        stmt.setString(2, user.getLogin());
        stmt.setString(3, String.valueOf(password.password(user)));
        ResultSet a = stmt.executeQuery();
        if (!a.next()) {
            PreparedStatement stm = (PreparedStatement) connection
                    .prepareStatement("UPDATE user SET login=?, password=? WHERE id=?");
            stm.setString(1, user.getLogin());
            stm.setString(2, String.valueOf(password.password(user)));
            stm.setInt(3, user.getId());
            stm.execute();
            return true;
        }
        return false;
    }

    @Override
    public String userX(User user) throws SQLException {
        Users users = Users.USER;
        Users manager = Users.MANAGER;
        Users admin = Users.ADMIN;
        Connection connection = ConnectionDAO.connection();
        PreparedStatement stmt = (PreparedStatement) connection
                .prepareStatement("SELECT login, password, user_level FROM user WHERE login=? AND password=?");
        stmt.setString(1, user.getLogin());
        stmt.setString(2, String.valueOf(password.password(user)));
        ResultSet a = stmt.executeQuery();
        String level = null;
        if (a.next()) {
           level = a.getString("user_level");
        }
        return level;
    }
}





