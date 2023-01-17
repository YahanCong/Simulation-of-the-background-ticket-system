package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DestinationTest {
    private Destination VancouverDest = new Destination("Vancouver");
    private List<Ticket> ticketsInDest = new ArrayList<>();
    private Ticket ticketVan = new FirstClassTicket("Jasper",VancouverDest,800);

    @BeforeEach
    public void setUp() {
        VancouverDest.addTicket(ticketVan);
        ticketsInDest.add(ticketVan);
    }

    @Test
    public void testGetters() {
        assertEquals("Vancouver",VancouverDest.getName());
        assertEquals(VancouverDest.getDestTicketList(),ticketsInDest);
    }

    @Test
    public void testAddTicket() {
        VancouverDest.addTicket(ticketVan);
        assertEquals(ticketsInDest,VancouverDest.getDestTicketList());
        assertEquals(VancouverDest,ticketVan.getDest());
        assertEquals(ticketsInDest,ticketVan.getDest().getDestTicketList());
    }


}
