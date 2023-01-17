package ui;

import model.BookingSystem;
import model.SearchSystem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SearchPanel extends JPanel implements ActionListener {
    Image background;
    MainGui mainGui;
    MainPanel mainPanel;
    BookingSystem bookingSystem;
    SearchSystem searchSystem;
    AdministratorPanel adPanel;
    JPanel searchingPanel;
    JButton confirmSearch;
    JTextField searchDest;

    String destAsked;
    String startAsked;

    public SearchPanel(MainGui mainGui, BookingSystem bookingSystem, AdministratorPanel adPanel) throws IOException {
        this.mainGui = mainGui;
        this.bookingSystem = bookingSystem;
        this.adPanel = adPanel;
        setLayout(null);
        searchSystem = new SearchSystem();
        background = ImageIO.read(new File("src/main/ui/via_background.jpg"));
        setSearchModel();
    }

    //EFFECT: construct a UI for search order
    private void setSearchModel() {
        JPanel searchBegin = new JPanel();
        searchBegin.setBackground(Color.white);
        Font adpw = new Font("Monaco",Font.PLAIN,18);
        JLabel searchStart = new JLabel("Please choose search way.");
        searchStart.setFont(adpw);
        searchBegin.add(searchStart);
        add(searchBegin);
        searchBegin.setBounds(200,50,500,40); //90

        setSearchMap();


        searchButtonSet();


        JButton backFromSearchWay = new JButton("Back");
        backFromSearchWay.setActionCommand("FromSearchBack");
        backFromSearchWay.addActionListener(this);
        add(backFromSearchWay);
        backFromSearchWay.setBounds(750,500,100,30);
    }

    //EFFECT: set a via map
    private void setSearchMap() {
        JPanel viaPanle = new JPanel();
        viaPanle.setVisible(true);
        viaPanle.setBackground(Color.white);
        Icon via = new ImageIcon("src/main/ui/trajets-region.gif");
        JLabel viaLabel = new JLabel(via,JLabel.CENTER);
        viaPanle.add(viaLabel);
        viaPanle.setBounds(60,150,800,350);
        add(viaPanle);
    }

    //EFFECT: create button for searching by destination and searching by departure city
    private void searchButtonSet() {
        JRadioButton destSearch = new JRadioButton("Search by destination");
        destSearch.setActionCommand("searchByDest");
        destSearch.addActionListener(this);
        JRadioButton startSearch = new JRadioButton("Search by departure city");
        startSearch.setActionCommand("searchByStart");
        startSearch.addActionListener(this);
        ButtonGroup searchButtonGroup = new ButtonGroup();
        searchButtonGroup.add(destSearch);
        searchButtonGroup.add(startSearch);
        add(destSearch);
        add(startSearch);
        destSearch.setBounds(150,100,200,40);  //150
        startSearch.setBounds(550,100,200,40);
    }


    //EFFECT: set background picture for this searchPanel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0,0, this);
    }

    //MODIFIES: this
    //EFFECT: provide what will system do after click different buttons.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("FromSearchBack")) {
            backToAdPanel();
        } else if (e.getActionCommand().equals("searchByDest")) {
            destSearchUI();

        } else if (e.getActionCommand().equals("confirmDestSearch")) {
            destAsked = searchDest.getText();
            JOptionPane.showMessageDialog(this, searchSystem.searchDestTicketResults(destAsked,bookingSystem));
        } else if (e.getActionCommand().equals("BackForSearch")) {
            removeAll();
            setSearchModel();
            updateUI();
        } else if (e.getActionCommand().equals("searchByStart")) {
            startSearchUI();
        } else if (e.getActionCommand().equals("confirmStartSearch")) {
            startAsked = searchDest.getText();
            JOptionPane.showMessageDialog(this, searchSystem.searchStartTicketResults(startAsked,bookingSystem));
        }

    }

    //MODIFIES: mainGUI, this
    //EFFECT: let UI go back to the start of SearchPanel.
    private void backToAdPanel() {
        mainGui.remove(this);
        mainGui.add(adPanel);
        adPanel.back();
        mainGui.pack();
    }

    //MODIFIES: this
    //EFFECT: constructor UI for searching by departure city
    private void startSearchUI() {
        removeAll();
        updateUI();
        searchingPanel = new JPanel();
        searchingPanel.setBackground(Color.white);
        add(searchingPanel);
        searchingPanel.setBounds(150,80,500,100);

        Font adpw = new Font("Monaco",Font.PLAIN,18);
        JLabel searchStartAsk = new JLabel("Please enter your departure city");
        searchStartAsk.setFont(adpw);
        searchingPanel.add(searchStartAsk);
        searchDest = new JTextField(10);
        searchingPanel.add(searchDest);
        searchingPanel.updateUI();

        destAsked = searchDest.getText();

        buttonSetForStart();
    }

    //MODIFIES: this
    //EFFECT: construct "confirm" and "back" button for start searching ui.
    private void buttonSetForStart() {
        confirmSearch = new JButton("Confirm");
        confirmSearch.setActionCommand("confirmStartSearch");
        confirmSearch.addActionListener(this);
        add(confirmSearch);
        confirmSearch.setBounds(600,500,100,30);

        JButton searchStartBack = new JButton("Back");
        searchStartBack.setActionCommand("BackForSearch");
        searchStartBack.addActionListener(this);
        add(searchStartBack);
        searchStartBack.setBounds(750,500,100,30);
    }

    //MODIFIES:this
    //EFFECT: constructor destination searching order UI
    private void destSearchUI() {
        removeAll();
        updateUI();
        searchingPanel = new JPanel();
        searchingPanel.setBackground(Color.white);
        add(searchingPanel);
        searchingPanel.setBounds(150,80,500,100);
        Font adpw = new Font("Monaco",Font.PLAIN,18);
        JLabel searchDestAsk = new JLabel("Please enter your destination");
        searchDestAsk.setFont(adpw);
        searchingPanel.add(searchDestAsk);
        searchDest = new JTextField(10);
        searchingPanel.add(searchDest);
        searchingPanel.updateUI();

        destAsked = searchDest.getText();

        buttonSetForDest();
    }

    //MODIFIES: this
    //EFFECT: create confirm and back button for destination search
    private void buttonSetForDest() {
        confirmSearch = new JButton("Confirm");
        confirmSearch.setActionCommand("confirmDestSearch");
        confirmSearch.addActionListener(this);
        add(confirmSearch);
        confirmSearch.setBounds(600,500,100,30);

        JButton searchDestBack = new JButton("Back");
        searchDestBack.setActionCommand("BackForSearch");
        searchDestBack.addActionListener(this);
        add(searchDestBack);
        searchDestBack.setBounds(750,500,100,30);
    }
}
