package model;

import exception.InvalidPriceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FirstClassTicketTest {

    private Ticket VanToJas;
    private Ticket TorToVan;
    private Ticket TorToOtt;
    private Ticket OttToVan;
    private Destination Vancouver;
    private Destination Jasper;
    private Destination Ottawa;

    @BeforeEach
    public void setUp() {
        Vancouver = new Destination("vancouver");
        Jasper = new Destination("Jasper");
        Ottawa = new Destination("Ottawa");


        VanToJas = new FirstClassTicket("",Jasper,205);
        VanToJas.setFrom("Vancouver");
        TorToVan = new FirstClassTicket("Toronto",null,608);
        TorToVan.setDest(Vancouver);
        TorToOtt = new FirstClassTicket("Toronto",Ottawa,0);
        TorToOtt.setPrice(109);
        OttToVan = new FirstClassTicket("Ottawa",Vancouver, 288);


    }

    @Test
    public void testgetters () {
        assertEquals("Vancouver",VanToJas.getFrom());
        assertEquals(Vancouver,TorToVan.getDest());
        assertEquals(109,TorToOtt.getPrice());

    }

    @Test
    public void testCheckPriceInRange() {
        try {
            OttToVan = new FirstClassTicket("Ottawa",Vancouver,3000);
            assertTrue(OttToVan.checkPriceInRange());
        } catch (InvalidPriceException e) {
            fail("Caught InvalidPriceException");

        }

        try {
            OttToVan = new BusinessTicket("Ottawa",Vancouver,30);
            assertTrue(OttToVan.checkPriceInRange());
            fail("Caught InvalidPriceException");
        } catch (InvalidPriceException e) {
            //expected


        }

    }

    @Test
    public void testEquals() {
        FirstClassTicket t1 = new FirstClassTicket("Jasper",Vancouver,600);
        FirstClassTicket t2 = new FirstClassTicket("Jasper",Vancouver,600);
        assertTrue(t1.equals(t2));
    }

    @Test
    public void testToString() {
        assertEquals("1 Vancouver Jasper 205.000000" + "\n",VanToJas.toString());
    }



}
