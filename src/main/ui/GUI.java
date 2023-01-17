package ui;

import exception.EmptyFileException;
import model.AccountCheck;
import model.BookingSystem;
import network.ReadWebPageEx;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI extends JPanel implements ActionListener {

    JLabel welcomeTitle;
    BookingSystem bookingSystem;
    private Image background;
    JFrame frame;
    String text;
    JTextField passwordField;
    JLabel verify;


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0, 0, this.getWidth(),this.getHeight(),this);
    }

    public GUI() throws IOException {
        //background = ImageIO.read(new File("src/main/ui/via_background.jpg"));
        background = ImageIO.read(new File("src/main/ui/via_background_new.jpg"));
        setTitleJPanel();
        bookingSystem = new BookingSystem();
        loadingFirst();
        setAdministratorOrExit();

    }

    //MODIFIES: this
    //EFFECTS: loading tickets in files first
    private void loadingFirst() {
        try {
            bookingSystem.loadFromFile();
        } catch (EmptyFileException e) {
            JLabel errorEmptyFile = new JLabel("SystemTickets is empty, there is nothing can be loaded in");
            add(errorEmptyFile);
        } catch (FileNotFoundException f) {
            JLabel errorNoFile = new JLabel("File SystemTicket is not exist, please create a loading file first");
            File newFile = new File("systemTickets");
            add(errorNoFile);
        }
    }


    //EFFECTS: set UI for enter administrator system or exit
    private void setAdministratorOrExit() {
        JPanel administratorPanel = new JPanel();
        add(administratorPanel);
        administratorPanel.setBackground(Color.yellow);
        administratorPanel.setVisible(true);


        JLabel typeChoose = new JLabel("Welcome, administrator");
        administratorPanel.add(typeChoose);


        JButton administrator = new JButton("Administrator");
        administrator.setActionCommand("administrator");
        administrator.addActionListener(this);
        add(administrator);

        JButton exit = new JButton("Exit");
        exit.setActionCommand("exit");
        exit.addActionListener(this);
        add(exit);

    }


    //MODIFIES: this
    //EFFECTS: set Welcome Panel
    private void setTitleJPanel() throws IOException {
        String welcomeMessage = ReadWebPageEx.readWebPageEx();
        welcomeTitle = new JLabel(welcomeMessage);
        add(welcomeTitle);
    }



    //EFFECTS: main method get Start from GUI
    public static void main(String[] args) throws IOException {
        startFromFrame();


    }


    //EFFECTS: create JFrame, get start from GUI
    private static void startFromFrame() throws IOException {
        JFrame frame = new JFrame("Welcome to VIA");
        frame.setPreferredSize(new Dimension(900,600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUI());

        frame.pack();
        frame.setVisible(true);
    }


    //EFFECTS: provide different reflection after click different buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("exit")) {
            System.exit(0);
        }
        if (e.getActionCommand().equals("administrator")) {
            verifyUI();
        }
        if (e.getActionCommand().equals("confirmPassword")) {
            text = passwordField.getText();
            if (AccountCheck.validatePassword(text)) {
                removeAll();
                updateUI();
                panelSetAfterVerify();
                setFourButtons();

            } else {
                verify.setText("Please enter right password");
                updateUI();
            }

        }

    }


    //EFFECTS: provide verify UI
    private void verifyUI() {
        removeAll();
        updateUI();
        passwordVerify();
        repaint();
    }


    //EFFECTS: repaint panel after verify password
    private void panelSetAfterVerify() {
        JPanel orderPanel = new JPanel();

        JLabel background = new JLabel("You have entered the background program. ");
        JLabel nextOrderPlease = new JLabel("Please enter next order. ");
        orderPanel.add(background);
        orderPanel.add(nextOrderPlease);

        orderPanel.setVisible(true);
        add(orderPanel);
    }


    //EFFECTS: set four buttons for next order
    private void setFourButtons() {
        JButton addTickets = new JButton("Add tickets ");
        JButton checkTickets = new JButton("2.check ticket list");
        JButton searchTickets = new JButton("3. search tickets with destination or departure");
        JButton enterManagerSystem = new JButton("4. enter manager system ");
        JButton quitAdSystem = new JButton("0. quit administrator system");

        add(addTickets);
        add(checkTickets);
        add(searchTickets);
        add(enterManagerSystem);
        add(quitAdSystem);
        repaint();
    }

    //MODIFIES: this
    //EFFECTS: provide UI to verify password
    private void passwordVerify() {
        JPanel verifyPassword = new JPanel();
        add(verifyPassword);
        verify = new JLabel("Please enter your administrator password.");
        verifyPassword.add(verify);
        passwordField = new JTextField(10);
        verifyPassword.add(passwordField);
        verifyPassword.setVisible(true);

        JButton confirmPassword = new JButton("Confirm");
        confirmPassword.setActionCommand("confirmPassword");
        confirmPassword.addActionListener(this);
        add(confirmPassword);
    }
}


