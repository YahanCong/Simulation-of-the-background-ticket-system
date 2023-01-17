package ui;

import exception.EmptyFileException;
import exception.InvalidPriceException;
import model.*;
import network.ReadWebPageEx;
import observers.Subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class BookingSystemUI extends Subject {

    private ReadWebPageEx readWebPageEx;
    private Scanner scan;
    private BookingSystem bookingSys;
    private SearchSystem searchSystem;
    private AccountCheck accountCheck;
    private EmployeeUI employeeUI;
    private int typeChosen;

    public BookingSystemUI() {
        readWebPageEx = new ReadWebPageEx();
        scan = new Scanner(System.in);
        bookingSys = new BookingSystem();
        searchSystem = new SearchSystem();
        accountCheck = new AccountCheck();
        employeeUI = new EmployeeUI();
        typeChosen = -1;
        addObservers(readWebPageEx);
        observersNotifyer();
        start();
    }


    //MODIFIES:this
    //EFFECTS: start to run the bookingSystem UI by loading from file "SystemTickets" first.
    public void start() {
        try {
            bookingSys.loadFromFile();
        } catch (EmptyFileException e) {
            System.out.println("SystemTickets is empty, there is nothing can be load in");
        } catch (FileNotFoundException f) {
            System.out.println("File SystemTicket is not exist, please create a loading file first");
            File newFile = new File("systemTickets");
        } finally {
            typeChooseAfterLoading();
        }
    }

    //EFFECTS: notify the observer and print welcome message.
    private void observersNotifyer() {
        try {
            notifyObservers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: after loading, choose your user's type. If you are an administrator, it will jump to administrator system
    //         if you choose 0, you will exit the system.
    private void typeChooseAfterLoading() {
        while (typeChosen != 0) {
            typeChosen = typeChoose();
            if (typeChosen == 1) {
                administrator();
            }
        }
        try {
            bookingSys.saveToFile();
        } catch (FileNotFoundException e) {
            File newFile = new File("systemTickets");
        }
    }


    //REQUIRES: The entered integer must be 1 or 0.
    //MODIFIES: this
    //EFFECTS: get user's type: 1 or 0.
    private int typeChoose() { //choose user type
        System.out.println("Please choose your user type: 1.administrator; 0.quit");
        return scan.nextInt();
    }

    //EFFECTS: verified administrator's password, and then choose the next order what administrator want to do next.
    public void administrator() {
        System.out.println("Please enter your administrator password.");
        String enterPassword = scan.next(); //enter administrator password, if enter "quit", log off from background
        while (true) {
            if (accountCheck.validatePassword(enterPassword)) {
                nextOrder();
                return;
            } else if (enterPassword.equals("quit")) {
                return;
            } else {
                System.out.println("Please enter right password");
                enterPassword = scan.next();
            }
        }
    }

    //REQUIRES: the entered integer should be one of {0,1,2,3,4}
    //MODIFIES: this, bookingSystem
    //EFFECTS: choose the next order: add ticket, check tickets, search tickets, or exit.
    private void nextOrder() {
        String nextOrderForContinue = printInfForNextOrder();
        while (true) {
            int next = scan.nextInt();
            if (next == 1) {
                addTicket();
            } else if (next == 2) {
                System.out.println(bookingSys.checkAvilTickets());
                System.out.println(nextOrderForContinue);
            } else if (next == 3) {
                ticketSearch();
                System.out.println(nextOrderForContinue);
            } else if (next == 4) {
                employeeUI.getStart(scan, bookingSys);

            } else if (next == 0) {
                return;
            }
        }
    }

    //EFFECTS: print the oriented message
    private static String printInfForNextOrder() {
        String nextOrderForContinue = "Would you like to continue? Please enter the next order. 1.add tickets 2.check "
                + "ticket list 3. search tickets with destination or departure 0. quit administrator system";
        System.out.println("You have entered the background program. Please enter next order. 1.add tickets 2.check "
                + "ticket " + "list 3. search tickets with destination or departure 4. enter manager system "
                + "0. quit administrator system");
        return nextOrderForContinue;
    }


    //REQUIRES: the entered integer for search way choosing should be 1 or 2.
    //EFFECTS: search tickets depends on destination or departure city.
    private void ticketSearch() {
        System.out.println("Please choose your search way: 1. destination 2.departure");
        int searchChoose = scan.nextInt();
        if (searchChoose == 1) {
            System.out.println("Please enter your destination");
            String cusDest = scan.next();
            String searchWithDestResult = searchSystem.searchDestTicketResults(cusDest,bookingSys);
            System.out.println(searchWithDestResult);
        } else if (searchChoose == 2) {
            System.out.println("Please enter your departure");
            String cusFrom = scan.next();
            String searchWithStartResult = searchSystem.searchStartTicketResults(cusFrom,bookingSys);
            System.out.println(searchWithStartResult);
        }
    }

    //REQUIRES: the entered price should in the three kind of tickets' price range.
    //MODIFIES: this, bookingSystem
    //EFFECTS: add new tickets into bookingSystem.
    private void addTicket() {
        System.out.println("Please enter destination");
        String dest = scan.next();
        System.out.println("Please enter departure city");
        String from = scan.next();
        System.out.println("Please enter ticket's type: 1. First Class Ticket "
                + "2. Economic Class Ticket 3. Business Class Ticket");
        int typeNum = scan.nextInt();
        System.out.println("Please enter ticket's price");
        double price = scan.nextDouble();

        addInfoCheck(dest, from, typeNum, price);

    }

    //REQUIRES: the entered price should in the three kind of tickets' price range.
    //MODIFIES: this, bookingSystem
    //EFFECTS: create a new ticket depends on the imputed information, and asks what the administrator want to do next.
    private void addInfoCheck(String dest, String from, int typeNum, double price) {
        try {
            bookingSys.addNewTicketToSys(dest, from, price, typeNum);
            System.out.println("Adding ticket successfully.");
        } catch (InvalidPriceException e) {
            System.out.println("Please enter valid price");
        } finally {
            System.out.println("Would you like to continue? Please enter the next order: "
                    + "1.add tickets 2.check ticket list 0. quit administrator system");
        }
    }



}
