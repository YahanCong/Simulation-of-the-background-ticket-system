package model;

import exception.InvalidPriceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessTicketTest {

    private Ticket VanToJas;
    private Ticket TorToVan;
    private Ticket TorToOtt;
    private Ticket OttToVan;
    private Ticket JasToVan;
    private Destination Vancouver;
    private Destination Jasper;
    private Destination Ottawa;

    @BeforeEach
    public void setUp() {
        Vancouver = new Destination("Vancouver");
        Jasper = new Destination("Jasper");
        Ottawa = new Destination("Ottawa");


        VanToJas = new BusinessTicket("",Jasper,205);
        VanToJas.setFrom("Vancouver");
        TorToVan = new BusinessTicket("Toronto",null,608);
        TorToVan.setDest(Vancouver);
        TorToOtt = new BusinessTicket("Toronto",Ottawa,0);
        TorToOtt.setPrice(109);

        JasToVan = new FirstClassTicket("Jasper",Vancouver,600);

    }

    @Test
    public void testgetters () {
        assertEquals("Vancouver",VanToJas.getFrom());
        assertEquals(Vancouver,TorToVan.getDest());
        assertEquals(109,TorToOtt.getPrice());
        assertEquals(3,TorToOtt.getTypeNum());

    }

    @Test
    public void testCheckPriceInRange() {
        try {
            OttToVan = new BusinessTicket("Ottawa",Vancouver,30);
            OttToVan.checkPriceInRange();
            fail("Caught InvalidPriceException");
        } catch (InvalidPriceException e) {
            assertEquals(e.getOutPutPriceMessage(),"Ticket Price you entered must in valid range: First Class: 500-10000; "
                    + "Economic Class 10-1000; Business Class: 100-2000");
        }

        try {
            OttToVan = new BusinessTicket("Ottawa",Vancouver,3000);
            OttToVan.checkPriceInRange();
            fail("Caught InvalidPriceException");
        } catch (InvalidPriceException e) {
            assertEquals(e.getOutPutPriceMessage(),"Ticket Price you entered must in valid range: First Class: 500-10000; "
                    + "Economic Class 10-1000; Business Class: 100-2000");
        }

        try {
            OttToVan = new BusinessTicket("Ottawa",Vancouver,300);
            OttToVan.checkPriceInRange();
        } catch (InvalidPriceException e) {
            fail("Caught InvalidPriceException");
        }

    }

    @Test
    public void testEquals() {
        BusinessTicket t1 = new BusinessTicket("Jasper",Vancouver,600);
        BusinessTicket t2 = new BusinessTicket("Jasper",Vancouver,600);
        assertTrue(t1.equals(t2));
    }

    @Test
    public void testToString() {
        assertEquals("3 Vancouver Jasper 205.000000" + "\n",VanToJas.toString());
    }

    @Test
    public void testTicketInDest() {
        Vancouver.addTicket(JasToVan);
        Vancouver.addTicket(TorToVan);
        List<Ticket> testTicketList = new ArrayList<>();

        testTicketList.add(TorToVan);
        testTicketList.add(JasToVan);
        assertEquals(testTicketList,Vancouver.getDestTicketList());
    }
}
