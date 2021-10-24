package by.academy.model;

import java.time.LocalDate;

public class Movie  {
    private int id;
    private String nameMovie;
    private LocalDate date;
    private String listTicket;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getDate() {
        return String.valueOf(date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getListTicket() {
        return listTicket;
    }

    public void setListTicket(String listTicket) {
        this.listTicket = listTicket;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", nameMovie='" + nameMovie + '\'' +
                ", date=" + date +
                ", listTicket='" + listTicket + '\'' +
                '}';
    }
}
