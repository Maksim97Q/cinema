package by.academy.service.impl;

import by.academy.dao.TicketDAO;
import by.academy.dao.impl.TicketDAOImpl;
import by.academy.model.Ticket;

import java.sql.SQLException;
import java.util.List;

public class TicketServiceImpl implements TicketDAO {

    TicketDAOImpl ticketDAOImpl;

    public TicketServiceImpl(TicketDAOImpl ticketDAOImpl) {
        this.ticketDAOImpl = ticketDAOImpl;
    }

    @Override
    public boolean buy() {
        return false;
    }

    @Override
    public List<Ticket> viewPurchasedTickets() {
        try {
            return ticketDAOImpl.viewPurchasedTickets();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
