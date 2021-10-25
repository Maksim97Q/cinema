package by.academy.service.impl;

import by.academy.dao.impl.UserDAOImpl;
import by.academy.model.User;
import by.academy.service.UserService;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserDAOImpl userDAOImpl;

    public UserServiceImpl(UserDAOImpl userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }

    @Override
    public boolean create(User user) {
        try {
            if (userDAOImpl.createUser(user)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        try {
            if (userDAOImpl.deleteUser(user)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        try {
           if (userDAOImpl.updateUser(user)) {
               return true;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String UserRole(User user) {
        try {
            return userDAOImpl.userX(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}



