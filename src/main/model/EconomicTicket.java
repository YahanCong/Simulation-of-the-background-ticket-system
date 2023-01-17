package model;

import exception.InvalidPriceException;


public class EconomicTicket extends Ticket {
    public static final String ECOMOMIC_CLASS_TYPE = "Economic Class";

    public EconomicTicket(String start, Destination dest, double price) {
        super(start, dest, price);

    }

    @Override
    public int getTypeNum() {
        return 2;
    }

    @Override
    public boolean checkPriceInRange() throws InvalidPriceException {
        boolean checkResult = true;
        String message = "";
        if (price < 10 || price >= 1000) {
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
        return obj instanceof EconomicTicket
                && this.dest.equals(((EconomicTicket)obj).dest)
                && this.from.equals(((EconomicTicket)obj).from)
                && this.price == (((EconomicTicket)obj).price);
    }
    

}
