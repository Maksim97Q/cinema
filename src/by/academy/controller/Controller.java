package by.academy.controller;

import by.academy.dao.ConnectionDAO;
import by.academy.model.Movie;
import by.academy.model.Ticket;
import by.academy.model.User;
import by.academy.service.impl.MovieServiceImpl;
import by.academy.service.impl.TicketServiceImpl;
import by.academy.service.impl.UserServiceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Controller implements ControllerStart {
    private TicketServiceImpl ticketServiceImpl;
    private UserServiceImpl userServiceImpl;
    private MovieServiceImpl movieServiceImpl;
    private User user;
    private Movie movie;


    public Controller(TicketServiceImpl ticketServiceImpl, MovieServiceImpl movieServiceImpl,
                      UserServiceImpl userServiceImpl, User user, Movie movie) {
        this.ticketServiceImpl = ticketServiceImpl;
        this.movieServiceImpl = movieServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.user = user;
        this.movie = movie;


    }

    @Override
    public void start() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("нажмите 0 для выхода из приложения");
            System.out.println("нажмите 1 для регистрации");
            System.out.println("нажмите 2 для входа в приложение");
            if (scanner.hasNextInt()) {
                int enter = scanner.nextInt();
                if (enter >= 0 && enter <= 2) {
                    if (enter == 0) {
                        ConnectionDAO.close();
                        System.out.println("---Пользователь завершил работу с приложением.");
                        break;
                    }
                    if (enter == 1)
                        registration();
                    if (enter == 2)
                        loginToTheApplication();
                } else System.err.println("введите цыфры от 0 до 2 \n");
            } else System.err.println("введите цыфры \n");
        }
    }

    public void menuAdmins() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("открыто меню для admins");
            System.out.println("выйти из меню admins нажмите 0");
            System.out.println("для удаления users нажмите 1");
            System.out.println("для изменения users нажмите 2");
            System.out.println("для удаления movies нажмите 3");
            System.out.println("для изменения movies нажмите 4");
            System.out.println("для добавления movies нажмите 5");
            if (scanner.hasNextInt()) {
                int enter = scanner.nextInt();
                if (enter >= 0 && enter <= 5) {
                    if (enter == 0)
                        break;
                    if (enter == 1) {
                        if (userServiceImpl.delete(deleteUsers(this.user)))
                            System.out.println("---пользователь удалён");
                        else System.err.println("нет такого пользователя");
                    }
                    if (enter == 2) {
                        if (userServiceImpl.update(updateUsers(this.user)))
                            System.out.println("---пользователь с id " + user.getId() + " изменён");
                        else System.err.println("пользователя с id " + user.getId() + " не существует");
                    }
                    if (enter == 3) {
                        if (movieServiceImpl.delete(deleteMovies(movie))) System.out.println("фильм успешно удалён");
                        else System.out.println("фильм с таким id и название не существует");
                    }
                    if (enter == 4) {
                        if (movieServiceImpl.update(updateMovies(this.movie)))
                            System.out.println("фильм с id " + movie.getId() + " изменён");
                        else System.out.println("фильма с id " + movie.getId() + " не существует");
                    }
                    if (enter == 5) {
                        if (movieServiceImpl.create(createMovies(this.movie))) System.out.println("фильм успешно добавлен");
                        else System.out.println("фильм с таким названием и датой уже есть");
                    }
                } else System.err.println("введите цыфры от 0 до 4");
            } else System.err.println("введите цыфры");
        }
    }

    public void menuManagers() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("открыто меню для managers");
            System.out.println("выйти из меню managers нажмите 0");
            System.out.println("для изменения movies нажмите 1");
            if (scanner.hasNextInt()) {
                int enter = scanner.nextInt();
                if (enter >= 0 && enter <= 3) {
                    if (enter == 0)
                        break;
                    if (enter == 1) {
                        if (movieServiceImpl.update(updateMovies(this.movie)))
                            System.out.println("фильм с id " + movie.getId() + " изменён");
                        else System.out.println("фильма с id " + movie.getId() + " не существует");
                    }
                } else System.err.println("введите цыфры от 0 до 1");
            } else System.out.println("введите цыфры");
        }
    }

    private void menuUsers() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("открыто меню для users");
            System.out.println("выйти из меню users нажмите 0");
            System.out.println("для просмотра movies нажмите 1");
            System.out.println("для покупки билета нажмите 2");
            System.out.println("для просмотра купленных билетов нажмите 3");
            if (scanner.hasNextInt()) {
                int enter = scanner.nextInt();
                if (enter >= 0 && enter <= 3) {
                    if (enter == 0)
                        break;
                    if (enter == 1) {
                        List<Movie> read = movieServiceImpl.read(this.movie);
                        read.forEach(System.out::println);
                        System.out.println("\n");
                    }
                    if (enter == 2) {

                    }
                    if (enter == 3) {
                        List<Ticket> tickets = ticketServiceImpl.viewPurchasedTickets();
                        tickets.forEach(System.out::println);
                        System.out.println("\n");
                    }
                } else System.err.println("введите цыфры от 0 до 3");
            } else System.err.println("введите цыфры");
        }
    }

    private void registration() {
        if (userServiceImpl.create(reads(this.user))) {
            System.out.println("---пользователь " + user.getLogin() + " зарегестрировался");
            System.out.println("---дата и время входа " + new SimpleDateFormat("yyyy.MM.dd / HH:mm:ss").format(Calendar.getInstance().getTime()));
            System.out.println("---------------------------------");
            menuUsers();
        } else {
            System.err.println("пользователь с таким логином есть");
            System.out.println("---------------------------------");
        }
    }

    public void loginToTheApplication() {
        String userRole = userServiceImpl.UserRole(reads(this.user));
        if (userRole != null) {
            if (userRole.equals("admin"))
                menuAdmins();
            if (userRole.equals("user"))
                menuUsers();
            if (userRole.equals("manager"))
                menuManagers();
        } else System.err.println("неверный логин или пароль \n");
    }

    public User reads(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-введите логин");
        String log = scanner.nextLine();
        user.setLogin(log);
        System.out.println("-введите пароль");
        String pass = scanner.nextLine();
        user.setPassword(pass);
        return user;
    }

    public Movie updateMovies(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-введите новый фильм");
        String movies = scanner.nextLine();
        movie.setNameMovie(movies);
        System.out.println("-введите новую дату в формате yyyy-MM-dd");
        String data = scanner.nextLine();
        if (data.matches("\\d{4}-\\d{2}-\\d{2}")) {
            movie.setDate(LocalDate.parse(data));
            System.out.println("-введите id фильма для изменения");
            int id = scanner.nextInt();
            movie.setId(id);
        } else {
            System.err.println("неверный формат даты");
            return updateMovies(movie);
        }
        return movie;
    }

    public User deleteUsers(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-введите логин и id пользователя для удаления");
        System.out.println("-введите логин");
        String delete = scanner.nextLine();
        user.setLogin(delete);
        System.out.println("-введите id");
        int id = scanner.nextInt();
        user.setId(id);
        return user;
    }
    public Movie createMovies(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-введите новое название фильма");
        String name = scanner.nextLine();
        movie.setNameMovie(name);
        System.out.println("-введите новую дату для фильма");
        String date = scanner.nextLine();
        movie.setDate(LocalDate.parse(date));
        System.out.println("-введите список билетов");
        String list = scanner.nextLine();
        movie.setListTicket(list);
        return movie;
    }

    public User updateUsers(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-введите новый логин");
        String log = scanner.nextLine();
        user.setLogin(log);
        System.out.println("-введите новый пароль");
        String pass = scanner.nextLine();
        user.setPassword(pass);
        System.out.println("-введите id пользователя для изменения");
        int id = scanner.nextInt();
        user.setId(id);
        return user;
    }

    public Movie deleteMovies(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-введите id и название фильма для его удаления");
        System.out.println("-введите название фильма");
        String name = scanner.nextLine();
        movie.setNameMovie(name);
        System.out.println("-введите id");
        int id = scanner.nextInt();
        movie.setId(id);
        return movie;
    }
}
