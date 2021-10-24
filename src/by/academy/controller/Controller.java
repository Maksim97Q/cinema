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
    private final UserServiceImpl userServiceImpl;
    private MovieServiceImpl movieServiceImpl;
    private final User user;
    private Movie movie;
    private Ticket ticket;

    public Controller(TicketServiceImpl ticketServiceImpl, MovieServiceImpl movieServiceImpl,
                      UserServiceImpl userServiceImpl, User user, Movie movie, Ticket ticket) {
        this.ticketServiceImpl = ticketServiceImpl;
        this.movieServiceImpl = movieServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.user = user;
        this.movie = movie;
        this.ticket = ticket;


    }

    @Override
    public void start() {

        System.out.println("вы вошли в приложение кинотеатр!");

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
                    if (enter == 1) {
                        registration();
                    }
                    if (enter == 2) {
                        loginToTheApplication();
                    }
                } else
                    System.err.println("введите цыфры от 0 до 2 \n");
            } else
                System.err.println("введите цыфры \n");
        }
    }
    public void choiceOfRole() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("открыто меню выбора пользователя");
            System.out.println("для выхода из меню нажмите 0");
            System.out.println("вы зашли как:");
            System.out.println("1 - обычный пользователь");
            System.out.println("2 - менеджер");
            System.out.println("3 - админ");
            if (scanner.hasNextInt()) {
                int enter = scanner.nextInt();
                if (enter == 0) {
                    break;
                }
                if (enter == 1) {
                    
                }
                if (enter == 2) {

                }
            }
        }
    }

    private void regularUserMenu() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("открыто главное меню");
            System.out.println("выйти из главного меню нажмите 0");
            System.out.println("для выбора фильма нажмите 1");
            System.out.println("для покупки билета нажмите 2");
            System.out.println("для росмотра купленных билетов нажмите 3");
            System.out.println("для редактирования фильма нажмите 4");
            System.out.println("для удаления пользователя нажмите 5");
            System.out.println("для изменения пользователя нажмите 6");
            if (scanner.hasNextInt()) {
                int enter = scanner.nextInt();
                if (enter >= 0 && enter <= 10) {
                    if (enter == 0) {
                        break;
                    }
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
                    if (enter == 4) {
                        movieServiceImpl.update(updateMovies(this.movie));
                    }
                    if (enter == 5) {
                        if (userServiceImpl.delete(deleteUsers(this.user))) {
                            System.out.println("---пользователь удалён");
                        } else {
                            System.err.println("нет такого пользователя");
                        }
                    }
                    if (enter == 6) {
                        if (userServiceImpl.update(updateUsers(this.user))) {
                            System.out.println("---пользователь с id " + user.getId() + " изменён");
                        } else {
                            System.err.println("пользователя с id " + user.getId() + " не существует");
                        }
                    }
                    if (enter == 7) {
                        movieServiceImpl.delete(movie);
                    }
                } else
                    System.err.println("введите цыфры от 0 до 3");
            } else
                System.err.println("введите цыфры");
        }
    }

    private void registration() {
        if (userServiceImpl.create(reads(this.user))){
            System.out.println("---пользователь " + user.getLogin() + " зарегестрировался");
            System.out.println("---дата и время входа " + new SimpleDateFormat("yyyy.MM.dd / HH:mm:ss").format(Calendar.getInstance().getTime()));
            System.out.println("---------------------------------");
            regularUserMenu();
        } else{
            System.err.println("пользователь с таким логином есть");
            System.out.println("---------------------------------");
        }
    }

    public void loginToTheApplication() {
        String s = userServiceImpl.UserX(reads(this.user));
        if (s.equals("admin")) {
            choiceOfRole();
        }
        if (s.equals("user")) {
            regularUserMenu();
        }
        if (s.equals("manager")) {

        }
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
        System.out.println("введите ");
        return movie;
    }
}
