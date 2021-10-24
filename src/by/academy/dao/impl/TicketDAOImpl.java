package by.academy.dao.impl;

import by.academy.dao.ConnectionDAO;
import by.academy.dao.TicketDAO;
import by.academy.model.Ticket;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    @Override
    public boolean buy() {
        return false;
    }
    List<Ticket> tickets = new ArrayList<>();

    @Override
    public List<Ticket> viewPurchasedTickets() throws SQLException {
        Connection connection = ConnectionDAO.connection();
        PreparedStatement stmt = (PreparedStatement) connection
                .prepareStatement("SELECT id_Movie, place_number, price, flag FROM " +
                        "ticket WHERE flag=?");
        stmt.setString(1, "bought");
        ResultSet resultSet = stmt.executeQuery();
        int a = 0;
        while (resultSet.next()) {
            tickets.add(new Ticket());
            tickets.get(a).setId_movie(resultSet.getInt("id_movie"));
            tickets.get(a).setPlaceNumber(resultSet.getInt("place_number"));
            tickets.get(a).setPrice(resultSet.getInt("price"));
            a++;
        }
        return tickets;
    }
}
