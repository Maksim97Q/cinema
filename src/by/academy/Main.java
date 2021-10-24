package by.academy;

import by.academy.controller.Controller;
import by.academy.controller.ControllerStart;
import by.academy.dao.Password;
import by.academy.dao.impl.MovieDAOImpl;
import by.academy.dao.impl.TicketDAOImpl;
import by.academy.dao.impl.UserDAOImpl;
import by.academy.model.Movie;
import by.academy.model.Ticket;
import by.academy.model.User;
import by.academy.service.impl.MovieServiceImpl;
import by.academy.service.impl.TicketServiceImpl;
import by.academy.service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        ControllerStart controller = new Controller(
                new TicketServiceImpl(new TicketDAOImpl()),
                new MovieServiceImpl(new MovieDAOImpl()),
                new UserServiceImpl(new UserDAOImpl(new Password())),
                new User(),
                new Movie(),
                new Ticket());
        controller.start();
    }
}
