package by.academy.dao;

import by.academy.model.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketDAO {
    boolean buy();
    List<Ticket> viewPurchasedTickets() throws SQLException;
}
