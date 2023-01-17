package ui;

import exception.InvalidPriceException;
import model.BookingSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AdministratorPanel extends JPanel implements ActionListener {
    Image background;
    MainGui mainGui;
    MainPanel mainPanel;
    BookingSystem bookingSystem;


    String destination;
    String startCity;
    String ticketPriceString;
    int typeChoosen;
    double ticketPrice;


    JPanel searchPanel;

    JLabel enterPrice;
    JLabel addDest;

    JTextField price;
    JTextField dest;
    JTextField start;
    JComboBox ticketType;
    JButton backFromAddTicket;

    public AdministratorPanel(MainGui mainGui, BookingSystem bookingSystem,MainPanel mainPanel) throws IOException {
        this.mainGui = mainGui;
        this.mainPanel = mainPanel;
        this.bookingSystem = bookingSystem;
        background = ImageIO.read(new File("src/main/ui/via_background_new.jpg"));
        setLayout(null);
        //background = ImageIO.read(new File("src/main/ui/via_background.jpg"));
        setVisible(true);
        createFourButtonsForNextOrder(mainPanel);
    }


    //EFFECT: set background picture for administrator Panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0,0, this.getWidth(),this.getHeight(),this);
    }




    //EFFECT: provide different reflection after click different buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addTs")) {
            addTicketToSystemUI();
        } else if (e.getActionCommand().equals("confirmAddTicket")) {
            addTicketsInToSys();
        } else if (e.getActionCommand().equals("checkTs")) {
            checkTicketsInSys();
        } else if (e.getActionCommand().equals("searchTicket")) {
            removeAll();
            enterSearchUI();
        }

    }

    //MODIFIES: MainGui
    //EFFECT: leave administrator panel and enter to search Panel to search
    public void enterSearchUI() {
        try {
            searchPanel = new SearchPanel(mainGui,bookingSystem,this);
            mainGui.add(searchPanel);
            mainGui.remove(this);
            mainGui.pack();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECT: provide UI for add ticket and add input ticket into system.
    private void addTicketToSystemUI() {
        removeAll();
        updateUI();
        addTicketUi();
    }

    //MODIFIES: this
    //EFFECT: provide add ticket UI: enter information and buttons
    private void addTicketUi() {
        JPanel addTicketPanel = new JPanel();
        add(addTicketPanel);

        destination = addTicketDest(addTicketPanel);
        startCity = addStart(addTicketPanel);
        typeChoosen = typeChoose();

        JPanel pricePanel = new JPanel();
        pricePanel.setBackground(Color.white);
        Font adpw = new Font("Monaco",Font.PLAIN,18);
        enterPrice = new JLabel("Please enter ticket's price");
        enterPrice.setFont(adpw);

        price = new JTextField(10);
        pricePanel.add(enterPrice);
        pricePanel.add(price);

        add(pricePanel);
        pricePanel.setBounds(250,270,400,40);


        setButtonsForAddTicket();
    }

    //MODIFIES: this
    //EFFECTS: set confirm and back button for add ticket
    private void setButtonsForAddTicket() {
        JButton confirmTicket = new JButton("Confirm");
        confirmTicket.setActionCommand("confirmAddTicket");
        confirmTicket.addActionListener(this);
        add(confirmTicket);
        confirmTicket.setBounds(600,500,100,30);

        backFromAddTicket = new JButton("Back");
        backFromAddTicket.setActionCommand("backFromAddTicket");
        backFromAddTicket.addActionListener(mainPanel);
        add(backFromAddTicket);
        backFromAddTicket.setBounds(750,500,100,30);
        repaint();
    }

    //MODIFIES: this
    //EFFECT: show all tickets in system on the screen and set back button
    public void checkTicketsInSys() {
        removeAll();
        updateUI();
        JPanel checkPanel = new JPanel();
        checkPanel.setBackground(Color.white);
        Font adpw = new Font("Monaco",Font.PLAIN,18);
        JLabel availableTicketsInSys = new JLabel(bookingSystem.checkAvilTickets());
        availableTicketsInSys.setFont(adpw);
        checkPanel.add(availableTicketsInSys);
        add(checkPanel);
        checkPanel.setBounds(200,150,500,500);

        back();
    }

    //MODIFIES: this
    //EFFECT: add back button to jump from checking to choose next order.
    public void back() {
        backFromAddTicket = new JButton("Back");
        backFromAddTicket.setActionCommand("backFromAddTicket");
        backFromAddTicket.addActionListener(mainPanel);
        add(backFromAddTicket);
        backFromAddTicket.setBounds(750,500,100,30);
        repaint();
    }

    //MODIFIES: this
    //EFFECT: get entered information and add ticket into system.
    private void addTicketsInToSys() {
        destination = dest.getText();
        startCity = start.getText();
        ticketPriceString = price.getText();
        ticketPrice = Double.parseDouble(ticketPriceString);
        addTicketIntoSystem();
    }

    //MODIFIES: bookingSystem
    //EFFECT: add tickets into bookingSystem
    private void addTicketIntoSystem() {
        try {
            bookingSystem.addNewTicketToSys(destination, startCity, ticketPrice, typeChoosen);
            JOptionPane.showMessageDialog(this, "Adding ticket successfully.");
            bookingSystem.saveToFile();
        } catch (InvalidPriceException invalidPrice) {
            JOptionPane.showMessageDialog(this, "Please enter valid price");
        } catch (FileNotFoundException noFile) {
            File newFile = new File("systemTickets");
        }


    }

    //MODIFIES: this
    //EFFECT: provide UI for choosing next order.
    private int typeChoose() {
        Font adpw = new Font("Monaco",Font.PLAIN,18);
        JLabel chooseTicketType = new JLabel("Please choose ticket's type");
        chooseTicketType.setFont(adpw);

        JPanel typeChoosen = new JPanel();
        typeChoosen.setBackground(Color.white);
        typeChoosen.add(chooseTicketType);

        ticketType = new JComboBox();
        ticketType.addItem("---Please choose ticket type---");
        ticketType.addItem("First Class Ticket");
        ticketType.addItem("Economic Class Ticket");
        ticketType.addItem("Business Class Ticket");
        typeChoosen.add(chooseTicketType);
        typeChoosen.add(ticketType);

        add(typeChoosen);
        typeChoosen.setBounds(220,210,450,40);
        return ticketType.getSelectedIndex() + 1;
    }

    //REQUIRES: string should be entered into JTextField.
    //MODIFIES: this
    //EFFECT: provide UI for add ticket start.
    private String addStart(JPanel addTicketPanel) {
        Font adpw = new Font("Monaco",Font.PLAIN,18);
        JLabel addStart = new JLabel("Please enter departure city");
        addStart.setFont(adpw);
        JPanel startFrom = new JPanel();
        startFrom.add(addStart);
        add(startFrom);
        startFrom.setVisible(true);
        startFrom.setBackground(Color.white);
        //addTicketPanel.add(addStart);

        start = new JTextField(10);
        startFrom.add(start);
        //addTicketPanel.add(start);
        //addTicketPanel.setBounds(300,350,500,40);
        startFrom.setBounds(200,150,500,40);
        startCity = start.getText();
        return startCity;
    }

    //REQUIRES: string should be entered into JTextField.
    //MODIFIES: this
    //EFFECT: provide UI for add ticket destination
    private String addTicketDest(JPanel addTicketPanel) {
        Font adpw = new Font("Monaco",Font.PLAIN,18);
        addDest = new JLabel("Please enter destination");
        addDest.setFont(adpw);

        JPanel from = new JPanel();
        from.add(addDest);
        //addTicketPanel.add(addDest);
        dest = new JTextField(10);
        //addTicketPanel.add(dest);
        from.add(dest);
        from.setBounds(190,90,500,40);
        from.setBackground(Color.white);

        add(from);
        destination = dest.getText();
        return destination;
    }

    //MODIFIES: this
    //EFFECT: set and add buttons for next order choices.
    private void createFourButtonsForNextOrder(MainPanel mainPanel) {
        JButton addTs = new JButton("      Add tickets    ");
        addTs.setActionCommand("addTs");
        addTs.addActionListener(this);
        JButton checkTs = new JButton("   check ticket list  ");
        checkTs.setActionCommand("checkTs");
        checkTs.addActionListener(this);
        JButton searchTs = new JButton("search ticket");
        searchTs.setActionCommand("searchTicket");
        searchTs.addActionListener(this);
        //JButton enterMS = new JButton("   enter manager system   ");
        //enterMS.setActionCommand("managerSystem");
        //enterMS.addActionListener(this);
        JButton quitAdS = new JButton("   quit administrator system  ");
        quitAdS.setActionCommand("quitAdS");
        quitAdS.addActionListener(mainGui);
        //addButtons(addTs, checkTs, searchTs, enterMS, quitAdS);
        addButtons(addTs, checkTs, searchTs, quitAdS);
    }

    //MODIFIES: this
    //EFFECT: add buttons for next order choices.
    private void addButtons(JButton addTs, JButton checkTs, JButton searchTs, JButton quitAdS) {
        add(addTs);
        addTs.setBounds(300,100,250,40);
        add(checkTs);
        checkTs.setBounds(300,200,250,40);
        add(searchTs);
        searchTs.setBounds(300,300,250,40);
        //add(enterMS);
        add(quitAdS);
        quitAdS.setBounds(300,400,250,40);
    }
}
