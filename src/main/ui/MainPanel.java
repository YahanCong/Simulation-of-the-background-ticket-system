package ui;

import exception.InvalidPriceException;
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

public class MainPanel extends JPanel implements ActionListener {

    JLabel welcomeTitle;
    JLabel verify;

    BookingSystem bookingSystem;

    Image background;
    String text;

    MainGui mainGui;

    JTextField passwordField;

    AdministratorPanel administratorPanel;


    public MainPanel(MainGui mainGui, BookingSystem bookingSystem) throws IOException {
        this.mainGui = mainGui;
        this.bookingSystem = bookingSystem;
        background = ImageIO.read(new File("src/main/ui/via_background_new.jpg"));
        setLayout(null);
        setWebWelcome();
        setAdministratorOrExit(mainGui);
        try {
            bookingSystem.saveToFile();
        } catch (FileNotFoundException noFile) {
            File newFile = new File("systemTickets");
        }

    }


    //EFFECTS: provide different reflection after click different buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("exit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("administrator")) {
            administratorUI();
        } else if (e.getActionCommand().equals("confirmPassword")) {
            try {
                enterNextOrder();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals("backFromAddTicket")) {
            returnBackToMainPanel();
        }

    }

    //REQUIRES: administratorPanel has been added into frame
    //MODIFIES: this, MainGUI
    //EFFECTS: jump from other panels to mainPanel
    public void returnBackToMainPanel() {
        removeAll();
        try {
            mainGui.remove(administratorPanel);
            //mainGui.removeAll();
            mainGui.add(this);
            enterAdministratorPanel();
            mainGui.pack();
            mainGui.setVisible(false);
            mainGui.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        repaint();
    }


    //MODIFIES: this
    //EFFECTS: provide administrator UI
    private void administratorUI() {
        removeAll();
        updateUI();
        passwordVerify(mainGui);
        repaint();
    }


    //MODIFIES: this
    //EFFECTS: enter administratorPanel for next order choice.
    private void enterNextOrder() throws IOException {
        text = passwordField.getText();
        if (AccountCheck.validatePassword(text)) {
            removeAll();
            enterAdministratorPanel();
        } else {
            verify.setText("Please enter right password");
            updateUI();
        }
    }


    //MODIFIES: this, MainGui
    //EFFECTS: enter administratorPanel
    public void enterAdministratorPanel() throws IOException {
        administratorPanel = new AdministratorPanel(mainGui,bookingSystem,this);
        mainGui.remove(this);
        mainGui.add(administratorPanel);
        mainGui.pack();
    }


    //MODIFIES: this
    //EFFECTS: set background.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0,0, this.getWidth(),this.getHeight(),this);
    }

    //MODIFIES: this
    //EFFECTS: set Welcome message on Panel
    private void setWebWelcome() throws IOException {
        JPanel welcome = new JPanel();
        welcome.setBackground(Color.white);
        String webWelcome = ReadWebPageEx.readWebPageEx();
        welcomeTitle = new JLabel(webWelcome);
        welcome.add(welcomeTitle);
        add(welcome);
        welcome.setBounds(300,50,300,60);
        updateUI();
    }


    //EFFECTS: set UI for enter administrator system or exit system.
    private void setAdministratorOrExit(MainGui mainGui) {
        JPanel administratorPanel = new JPanel();
        add(administratorPanel);
        administratorPanel.setBackground(Color.white);
        administratorPanel.setVisible(true);
        administratorPanel.setBounds(300,100,300,60);

        Font pwFont = new Font("Monaco",Font.PLAIN,20);
        JLabel typeChoose = new JLabel("Welcome, administrator");
        typeChoose.setFont(pwFont);
        administratorPanel.add(typeChoose);


        buttonSetForAd();
    }

    //EFFECTS: set button for enter administrator system or exit.
    private void buttonSetForAd() {
        JButton administrator = new JButton("    Administrator    ");
        administrator.setActionCommand("administrator");
        administrator.addActionListener(this);
        add(administrator);
        administrator.setBounds(350,200,200,40);

        JButton exit = new JButton("Exit");
        exit.setActionCommand("exit");
        exit.addActionListener(this);
        add(exit);
        exit.setBounds(350,280,200,40);
    }

    //MODIFIES: this
    //EFFECTS: provide UI for verify administrator password.
    private void passwordVerify(MainGui mainGui) {
        JPanel verifyPassword = new JPanel();
        //verifyPassword.setLayout(null);
        add(verifyPassword);

        Font adpw = new Font("Monaco",Font.PLAIN,18);
        verify = new JLabel("Please enter your administrator password.");
        verify.setFont(adpw);
        verifyPassword.add(verify);
        verifyPassword.setBackground(Color.white);
        verifyPassword.setBounds(170,100,500,60);
        verifyPassword.setVisible(true);
        passwordField = new JTextField(10);
        verifyPassword.add(passwordField);

        buttonCreate(mainGui);
    }


    //EFFECTS: create confirm and back buttons
    private void buttonCreate(MainGui mainGui) {
        JButton confirmPassword = new JButton("Confirm");
        confirmPassword.setActionCommand("confirmPassword");
        confirmPassword.addActionListener(this);
        add(confirmPassword);
        confirmPassword.setBounds(600,500,100,30);

        JButton backFromPassword = new JButton("Back");
        backFromPassword.setActionCommand("backFromPassword");
        backFromPassword.addActionListener(mainGui);
        add(backFromPassword);
        backFromPassword.setBounds(750,500,100,30);
        updateUI();
    }
}
