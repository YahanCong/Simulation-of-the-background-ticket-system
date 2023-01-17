package ui;


import java.io.IOException;

public class Main {

    //EFFECTS： the main method for start the whole VIA system.
    public static void main(String[] args) throws IOException {

        GUI gui = new GUI();

        BookingSystemUI bookingSystemUI = new BookingSystemUI();
        bookingSystemUI.start();
    }
}
