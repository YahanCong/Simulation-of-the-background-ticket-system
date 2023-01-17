package model;

import exception.EmptyFileException;
import exception.InvalidPriceException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BookingSystem implements SaveFiles, LoadFiles {
    private ArrayList<Ticket> availableTickets; //available tickets list
    private HashMap<String, Destination> dests;


    public BookingSystem() {
        availableTickets = new ArrayList<>();
        dests = new HashMap<>();
    }


    //REQUIRES: dest != null; from != null; price != null
    //EFFECTS: create a new ticket with input information, and add ticket into available ticket list
    public void addNewTicketToSys(String dest, String from, double price, int typeNum) throws InvalidPriceException {
        boolean destExist = dests.containsKey(dest);
        Destination destToAddTicket;
        if (!destExist) {
            destToAddTicket = new Destination(dest);
            //dests.put(dest, destToAddTicket);
        } else {
            destToAddTicket = dests.get(dest);
        }

        ticketTypeChoose(from, price, typeNum, destToAddTicket);

    }

    private void ticketTypeChoose(String from, double price, int typeNum, Destination destToAddTicket) {
        if (typeNum == 1) {
            firstClassTicketCreate(from, price, destToAddTicket);
        } else if (typeNum == 2) {
            economicTicketCreate(from, price, destToAddTicket);
        } else if (typeNum == 3) {
            businessTicketCreate(from, price, destToAddTicket);
        }
    }

    private void businessTicketCreate(String from, double price, Destination destToAddTicket) {
        Ticket ticket = new BusinessTicket(from, destToAddTicket, price);
        if (ticket.checkPriceInRange()) {
            addTicket(ticket);
            //dests.get(destToAddTicket.getName()).getDestTicketList().add(ticket);
        }
    }

    private void economicTicketCreate(String from, double price, Destination destToAddTicket) {
        Ticket ticket = new EconomicTicket(from, destToAddTicket, price);
        if (ticket.checkPriceInRange()) {
            addTicket(ticket);
            //dests.get(destToAddTicket.getName()).getDestTicketList().add(ticket);
        }
    }

    private void firstClassTicketCreate(String from, double price, Destination destToAddTicket) {
        Ticket ticket = new FirstClassTicket(from, destToAddTicket, price);
        if (ticket.checkPriceInRange()) {
            addTicket(ticket);
            //dests.get(destToAddTicket.getName()).getDestTicketList().add(ticket);
        }
    }


    //MODIFIES: availableTickets
    //EFFECTS: add new tickets into ArrayList availableTickets.
    public void addTicket(Ticket t) { // add tickets into ticket available list
        availableTickets.add(t);
        t.getDest().addTicket(t);
        dests.put(t.getDest().getName(), t.getDest());
    }

    //EFFECTS: get all tickets in ArrayList availableTickets as a String.
    public String checkAvilTickets() {  //print available ticket list
        StringBuilder checkAvilList = new StringBuilder();
        if (null == availableTickets || availableTickets.size() == 0) {
            checkAvilList = new StringBuilder("Ticket list is empty now.");
        } else {
            for (Ticket ticket : availableTickets) {
                checkAvilList.append("<html><body>");
                checkAvilList.append("From: ").append(ticket.getFrom());
                checkAvilList.append(" To: ").append(ticket.getDest().getName()).append(" Price:");
                checkAvilList.append(ticket.getPrice()).append(" Type:");
                checkAvilList.append(String.format("%d", ticket.getTypeNum())).append("<br>");
                //checkAvilList.append(String.format("%d", ticket.getTypeNum())).append("\n");
            }
        }
        return checkAvilList.toString();
    }

    //getters
    public ArrayList<Ticket> getAvailableTickets() {
        return availableTickets;
    }


    //EFFECTS: save the available tickets and shopping cart into local files
    @Override
    public void saveToFile() throws FileNotFoundException {
        saveToSystemTickets();
    }

    private void saveToSystemTickets() throws FileNotFoundException {
        /*
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("systemTickets"), Charset.defaultCharset());
            //lines = Files.readAllLines(Paths.get("systemTickets"));
        } catch (IOException e) {
            System.out.println("IOException");
        }

         */

        PrintWriter outFirstClass = new PrintWriter("systemTickets");

        /*
        for (String line : lines) {
            outFirstClass.write(line);
        }

         */


        for (Ticket ticket : availableTickets) {
            outFirstClass.write(ticket.toString());
        }
        outFirstClass.close();
    }


    //EFFECTS: load the available tickets and shopping cart from local files.
    @Override
    public void loadFromFile() throws FileNotFoundException, EmptyFileException {
        File file = new File("systemTickets");
        if (file.length() == 0) {
            String message = "";
            throw new EmptyFileException(message);
        }
        Scanner in = new Scanner(new File("systemTickets"));
        availableTickets.clear();
        while (in.hasNextLine()) {
            String ticket = in.nextLine();
            String[] s = ticket.split(" ");
            addNewTicketToSys(s[2], s[1], Double.parseDouble(s[3]), Integer.parseInt(s[0]));
        }
    }

    public HashMap<String, Destination> getDests() {
        return dests;
    }
}

