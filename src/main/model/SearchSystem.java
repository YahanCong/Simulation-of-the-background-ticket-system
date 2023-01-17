package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchSystem {
    private HashMap<String, ArrayList<Ticket>> startMap = new HashMap<>();
    private HashMap<String, Destination> dests;
    private ArrayList<Ticket> availableTickets;

    public SearchSystem() {
        dests = new HashMap<>();
        availableTickets = new ArrayList<>();
    }


    //EFFECTS: search all the ticket with same departure city from startMap and print them
    public String searchStartTicketResults(String cusFrom, BookingSystem bookingSystem) {
        prepareToSearch(bookingSystem);
        ArrayList<Ticket> ticketArrayList = searchTicketWithStart(cusFrom);
        String printSearchResult = "";
        if (!ticketArrayList.isEmpty()) {
            for (Ticket t: ticketArrayList) {
                String printSearchTicket;
                printSearchTicket = "To: " + t.getDest().getName() + " From:" + t.getFrom() + " Type: " + t.getTypeNum()
                        + " Price: " + t.getPrice() + "\n";
                printSearchResult = printSearchResult + printSearchTicket;

            }
            return printSearchResult;
        } else {
            return "There is no ticket with the given departure";
        }


    }

    //MODIFIES: this
    //EFFECTS: To prepare ticking searching, get available list and dests HashMap from booking system class
    private void prepareToSearch(BookingSystem bookingSystem) {
        dests = bookingSystem.getDests();
        availableTickets = bookingSystem.getAvailableTickets();
    }

    //EFFECTS: search all the ticket with destination from dests HashMap and print them
    public String searchDestTicketResults(String cusDest, BookingSystem bookingSystem) {
        prepareToSearch(bookingSystem);
        List<Ticket> ticketArrayList = dests.get(cusDest).getDestTicketList();
        String printSearchResult = "";
        if (!ticketArrayList.isEmpty()) {
            for (Ticket t: ticketArrayList) {
                String printSearchTicket;
                printSearchTicket = "<html><body>" + "To: " + t.getDest().getName() + " From:" + t.getFrom()
                        + " Type: " + t.getTypeNum()
                        + " Price: " + t.getPrice() + "<br>";
                printSearchResult = printSearchResult + printSearchTicket;

            }
            return printSearchResult;
        } else {
            return "There is no ticket with the given destination";
        }


    }





    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public ArrayList<Ticket> searchTicketWithStart(String cusFrom) {
        ticketWithSameStartCollection();
        return startMap.get(cusFrom);
    }



    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void ticketWithSameStartCollection() {
        for (Ticket ticket: availableTickets) {
            ArrayList<Ticket> ticketList = new ArrayList<>();
            for (Ticket t: availableTickets) {
                if (ticket.getFrom().equals(t.getFrom())) {
                    ticketList.add(t);
                }
            }
            startMap.put(ticket.getFrom(),ticketList);
        }

    }




}
