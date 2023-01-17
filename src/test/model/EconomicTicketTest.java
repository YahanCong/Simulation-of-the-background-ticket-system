package model;

import exception.InvalidPriceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EconomicTicketTest {

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


        VanToJas = new EconomicTicket("",Jasper,205);
        VanToJas.setFrom("Vancouver");
        TorToVan = new EconomicTicket("Toronto",null,608);
        TorToVan.setDest(Vancouver);
        TorToOtt = new EconomicTicket("Toronto",Ottawa,0);
        TorToOtt.setPrice(109);
        //OttToVan = new EconomicTicket("Ottawa","Vancouver", 288);

    }

    @Test
    public void testgetters () {
        assertEquals("Vancouver",VanToJas.getFrom());
        assertEquals(Vancouver,TorToVan.getDest());
        assertEquals(109,TorToOtt.getPrice());
        assertEquals(2,TorToOtt.getTypeNum());
    }

    @Test
    public void testCheckPriceInRange() {
        try {
            OttToVan = new EconomicTicket("Ottawa",Vancouver,3000);
        } catch (InvalidPriceException e) {
            fail("Caught InvalidPriceException");
        }




        try {
            OttToVan = new EconomicTicket("Ottawa",Vancouver,300);
        } catch (InvalidPriceException e) {
            fail("Caught InvalidPriceException");
            //expected


        }

    }

    @Test
    public void testEquals() {
        EconomicTicket t1 = new EconomicTicket("Jasper",Vancouver,300);
        EconomicTicket t2 = new EconomicTicket("Jasper",Vancouver,300);
        assertTrue(t1.equals(t2));
    }

    @Test
    public void testToString() {
        assertEquals("2 Vancouver Jasper 205.000000" + "\n",VanToJas.toString());
    }



}
