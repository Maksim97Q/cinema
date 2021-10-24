package by.academy.service;

import by.academy.model.Ticket;

import java.util.List;

public interface TicketService {
    boolean buy();
    List<Ticket> viewPurchasedTickets();
}
