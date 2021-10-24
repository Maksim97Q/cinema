package by.academy.service;

import by.academy.model.User;

public interface UserService {
    boolean create(User user);

    boolean delete(User user);

    boolean update(User user);

    String UserX(User user);
}
