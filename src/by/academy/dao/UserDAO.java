package by.academy.dao;

import by.academy.model.User;
import java.sql.SQLException;

public interface UserDAO {
    boolean createUser(User user) throws SQLException;

    boolean deleteUser(User user) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    String userX (User user) throws SQLException;
}
