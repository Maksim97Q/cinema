package by.academy.model;

public class Ticket {
    private int id_movie;
    private int placeNumber;
    private int price;
    private String flag;

    public int getId_movie() {
        return id_movie;
    }

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id_movie=" + id_movie +
                ", placeNumber=" + placeNumber +
                ", price=" + price +
                '}';
    }
}
