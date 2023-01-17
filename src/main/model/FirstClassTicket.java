package model;

import exception.InvalidPriceException;


public class FirstClassTicket extends Ticket {
    public static final String FIRST_CLASS_TYPE = "First Class";


    public FirstClassTicket(String start, Destination dest, double price) {
        super(start, dest, price);
    }

    @Override
    public int getTypeNum() {
        return 1;
    }

    @Override
    public boolean checkPriceInRange() throws InvalidPriceException {
        boolean checkResult = true;
        String message = "";
        if (price < 500 || price > 10000) {
            checkResult = false;
            throw new InvalidPriceException(message);
        }
        return checkResult;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FirstClassTicket
                && this.dest.equals(((FirstClassTicket)obj).dest)
                && this.from.equals(((FirstClassTicket)obj).from)
                && this.price == (((FirstClassTicket)obj).price);
    }
}
