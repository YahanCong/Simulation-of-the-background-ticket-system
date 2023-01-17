package model;

import static org.junit.jupiter.api.Assertions.*;

import exception.EmptyFileException;
import exception.InvalidPriceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BookingSystemTest {

    private Destination Vancouver = new Destination("Vancouver");
    private Destination Jasper = new Destination("Jasper");
    private Destination Ottawa = new Destination("Ottawa");
    private Destination Banff = new Destination("Banff");
    private Destination Quebec = new Destination("Quebec");


    private Ticket VanToJas = new FirstClassTicket("Vancouver",Jasper,505);
    private Ticket TorToVan = new EconomicTicket("Toronto",Vancouver,608);
    private Ticket TorToOtt = new BusinessTicket("Toronto",Ottawa,109);

    private Ticket JasToBan = new FirstClassTicket("Jasper",Banff,507);
    private Ticket JasToVan = new EconomicTicket("Jasper",Vancouver,450);


    private BookingSystem bookingSystem;


    @BeforeEach
    public void setBookingSystem() throws FileNotFoundException {
        bookingSystem = new BookingSystem();

        bookingSystem.addTicket(VanToJas);
        bookingSystem.addTicket(TorToVan);
        bookingSystem.addTicket(TorToOtt);
    }

    @Test
    public void getterTest() {
        ArrayList testAvailableTicket = new ArrayList();
        testAvailableTicket.add(VanToJas);
        testAvailableTicket.add(TorToVan);
        testAvailableTicket.add(TorToOtt);
        assertEquals(bookingSystem.getAvailableTickets(),testAvailableTicket);

    }




    @Test
    public void addNewTicketToSysTest() {
        ArrayList<Ticket> testAvailableTickets = new ArrayList<>();

        bookingSystem.addNewTicketToSys("Banff","Jasper",507,1);

        testAvailableTickets.add(VanToJas);
        testAvailableTickets.add(TorToVan);
        testAvailableTickets.add(TorToOtt);
        testAvailableTickets.add(JasToBan);

        ArrayList<Ticket> gotAvilList = bookingSystem.getAvailableTickets();

        boolean checkAvilTicketTest = false;
        for(int i = 1;i <gotAvilList.size();i++) {
            if (gotAvilList.get(i).getDest().getName().equals(testAvailableTickets.get(i).getDest().getName()) &&
                    gotAvilList.get(i).getFrom().equals(testAvailableTickets.get(i).getFrom()) &&
                            gotAvilList.get(i).getPrice() ==
                                    gotAvilList.get(i).getPrice()) {
                checkAvilTicketTest = true;
            } else {
                checkAvilTicketTest = false;
            }
        }
        assertTrue(checkAvilTicketTest);

        bookingSystem.addNewTicketToSys("Quebec","Montreal",187,2);
        Ticket MonToQue = new FirstClassTicket("Mon",Quebec,187);
        testAvailableTickets.add(MonToQue);
        for(int i = 1;i <gotAvilList.size();i++) {
            if (gotAvilList.get(i).getDest().equals(testAvailableTickets.get(i).getDest()) &&
                    gotAvilList.get(i).getFrom().equals(testAvailableTickets.get(i).getFrom()) &&
                    gotAvilList.get(i).getPrice() ==
                            gotAvilList.get(i).getPrice()) {
                checkAvilTicketTest = true;
            } else {
                checkAvilTicketTest = false;
            }
        }
        assertFalse(checkAvilTicketTest);

    }


    @Test
    public void addTicketTest() {
        ArrayList<Ticket> testAvailableTickets = new ArrayList<>();

        testAvailableTickets.add(VanToJas);
        testAvailableTickets.add(TorToVan);
        testAvailableTickets.add(TorToOtt);
        testAvailableTickets.add(JasToBan);

        bookingSystem.addTicket(JasToBan);
        assertEquals(bookingSystem.getAvailableTickets(),testAvailableTickets);


        Ticket MonToQue = new FirstClassTicket("Montreal",Quebec,187);
        testAvailableTickets.add(MonToQue);
        bookingSystem.addTicket(MonToQue);
        assertEquals(bookingSystem.getAvailableTickets(),testAvailableTickets);


    }

    @Test
    public void checkAvilTicketsTest() {
        assertEquals(bookingSystem.checkAvilTickets(),"<html><body>From: Vancouver " +
                "To: Jasper Price:505.0 Type:1<br><html><body>From: Toronto To: Vancouver Price:608.0 " +
                "Type:2<br><html><body>From: Toronto " +
                "To: Ottawa Price:109.0 Type:3<br>");
        /*
        assertEquals(bookingSystem.checkAvilTickets(),"From: Vancouver To: Jasper Price:505.0 Type:1" + "\n" +
                "From: Toronto To: Vancouver Price:608.0 Type:2" + "\n" +
                "From: Toronto To: Ottawa Price:109.0 Type:3" + "\n");

         */

        bookingSystem.addTicket(JasToBan);
        assertEquals(bookingSystem.checkAvilTickets(),"<html><body>From: Vancouver To: Jasper Price:505.0 " +
                "Type:1<br><html><body>From: Toronto To: Vancouver Price:608.0 " +
                "Type:2<br><html><body>From: Toronto To: Ottawa Price:109.0 " +
                "Type:3<br><html><body>From: Jasper To: Banff Price:507.0 Type:1<br>");
        /*
        assertEquals(bookingSystem.checkAvilTickets(),"From: Vancouver To: Jasper Price:505.0 Type:1" + "\n" +
                "From: Toronto To: Vancouver Price:608.0 Type:2" + "\n" +
                "From: Toronto To: Ottawa Price:109.0 Type:3" + "\n" +
                "From: Jasper To: Banff Price:507.0 Type:1" + "\n");

         */
    }




    @Test
    public void bookingSystemSavingTest() throws IOException {
        bookingSystem.addTicket(VanToJas);
        File file = new File("systemTickets");
        bookingSystem.saveToFile();
        List<String> linesForTickets = Files.readAllLines(Paths.get("systemTickets"));
        assertTrue(linesForTickets.contains("1 Vancouver Jasper 505.000000"));
    }

    @Test
    public void bookingSystemLoadingTest() throws IOException {
        bookingSystem.addTicket(TorToVan);
        bookingSystem.saveToFile();
        File file = new File("systemTickets");
        String str;
        int count = 0;
        FileReader fr = new FileReader("systemTickets");
        BufferedReader bfr =new BufferedReader(fr);
        while((str = bfr.readLine()) != null){
            count++;
        }


        try {
            bookingSystem.loadFromFile();
        } catch (EmptyFileException e) {
            System.out.println("The file is empty, nothing loaded now");
        } catch (FileNotFoundException e) {
            File newFile = new File("systemTickets");
        } finally {
            int availTicketNum = 0;
            for(int i = 0; i< bookingSystem.getAvailableTickets().size(); i++) {
                availTicketNum++;
            }
            assertEquals(availTicketNum, count);
        }
    }

    @Test
    public void addNewTicketToSystemExcpetion() {
        try {
            bookingSystem.addNewTicketToSys("e","f",100,1);
            fail("Caught InvalidPriceException");
        } catch (InvalidPriceException e) {
            //expected
        }


        try {
            bookingSystem.addNewTicketToSys("f","e",100,2);
        } catch (InvalidPriceException e) {
            fail("Caught InvalidPriceException");
        }

    }






}
