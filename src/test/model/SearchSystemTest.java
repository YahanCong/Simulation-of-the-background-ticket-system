package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class SearchSystemTest {

    private Destination Vancouver = new Destination("Vancouver");
    private Destination Jasper = new Destination("Jasper");
    private Destination Ottawa = new Destination("Ottawa");
    private Destination Banff = new Destination("Banff");


    private Ticket VanToJas = new FirstClassTicket("Vancouver",Jasper,505);
    private Ticket TorToVan = new EconomicTicket("Toronto",Vancouver,608);
    private Ticket TorToOtt = new BusinessTicket("Toronto",Ottawa,109);

    private Ticket JasToBan = new FirstClassTicket("Jasper",Banff,507);
    private Ticket JasToVan = new EconomicTicket("Jasper",Vancouver,450);


    private BookingSystem bookingSystem;
    private SearchSystem searchSystem;


    @BeforeEach
    public void setBookingSystem() throws FileNotFoundException {
        bookingSystem = new BookingSystem();

        bookingSystem.addTicket(VanToJas);
        bookingSystem.addTicket(TorToVan);
        bookingSystem.addTicket(TorToOtt);
        searchSystem = new SearchSystem();
    }

    @Test
    public void testSearchTicketWithDest() {
        String testString = searchSystem.searchDestTicketResults("Jasper",bookingSystem);
        assertEquals(testString,"<html><body>To: Jasper From:Vancouver Type: 1 Price: 505.0<br>");
        /*
        assertEquals(testString,
                "To: Jasper From:Vancouver Type: 1 Price: 505.0" + "\n");

         */
        assertEquals(testString,"<html><body>To: Jasper From:Vancouver Type: 1 Price: 505.0<br>");
        /*
        assertEquals(searchSystem.searchDestTicketResults("Vancouver",bookingSystem),
                "To: Vancouver From:Toronto Type: 2 Price: 608.0" + "\n");

         */
    }

    @Test
    public void testSearchTicketWithStart() {
        assertEquals(searchSystem.searchStartTicketResults("Vancouver", bookingSystem),
                "To: Jasper From:Vancouver Type: 1 Price: 505.0" + "\n");
        assertEquals(searchSystem.searchStartTicketResults("Toronto", bookingSystem),
                "To: Vancouver From:Toronto Type: 2 Price: 608.0\n" +
                        "To: Ottawa From:Toronto Type: 3 Price: 109.0" + "\n");
    }
}
