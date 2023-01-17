package model;

import java.util.ArrayList;
import java.util.List;

public class Destination {

    private String name;
    private List<Ticket> destTicketList;

    public Destination(String name) {
        destTicketList = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Ticket> getDestTicketList() {
        return destTicketList;
    }

    public void addTicket(Ticket ticket) {
        if (!destTicketList.contains(ticket)) {
            destTicketList.add(ticket);
            ticket.setDest(this);
        }
    }



}
