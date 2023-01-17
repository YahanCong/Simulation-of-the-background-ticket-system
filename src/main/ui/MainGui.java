package ui;

import exception.EmptyFileException;
import model.BookingSystem;
import model.SearchSystem;
import sun.applet.AppletAudioClip;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainGui extends JFrame implements ActionListener {
    BookingSystem bookingSystem;

    public MainGui() throws IOException {
        bookingSystem = new BookingSystem();
        setTitle("Welcome to VIA");
        loadingFirst();



    }

    private void loadingFirst() throws FileNotFoundException {
        try {
            bookingSystem.loadFromFile();
        } catch (EmptyFileException e) {
            JOptionPane.showMessageDialog(this,"SystemTickets is empty, "
                    + "there is nothing can be loaded in");
        } catch (FileNotFoundException f) {
            JOptionPane.showMessageDialog(this,"File SystemTicket is not exist, "
                    + "please create a loading file first");
            File newFile = new File("systemTickets");
        } finally {
            bookingSystem.saveToFile();
        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("backFromPassword")) {
            try {
                showMainPanel();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equalsIgnoreCase("quitAdS")) {
            try {
                showMainPanel();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }


    public static void main(String[] args) throws IOException {
        AudioClip ac = Applet.newAudioClip((new File("src/main/music/backgroundMusic_new.wav")).toURI().toURL());
        ac.loop();

        MainGui mainGui = new MainGui();
        mainGui.setPreferredSize(new Dimension(900,600));
        mainGui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainGui.pack();
        mainGui.setVisible(true);

        mainGui.showMainPanel();
        mainGui.pack();

    }


    private void showMainPanel() throws IOException {
        getContentPane().removeAll();
        MainPanel mainPanel = new MainPanel(this,bookingSystem);
        add(mainPanel);
        this.setVisible(false);
        this.setVisible(true);
    }
}
