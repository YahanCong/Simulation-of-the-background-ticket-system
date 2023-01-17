package model;

import exception.InvalidPriceException;



public class BusinessTicket extends Ticket {
    public static final String BUSINESS_CLASS_TYPE = "Business Class";


    public BusinessTicket(String start, Destination dest, double price) {
        super(start, dest, price);
    }

    @Override
    public int getTypeNum() {
        return 3;
    }

    @Override
    public boolean checkPriceInRange() throws InvalidPriceException {
        boolean checkResult = true;
        String message = "";
        if (price < 100 || price >= 2000) {
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
        return obj instanceof BusinessTicket
                && this.dest.equals(((BusinessTicket)obj).dest)
                && this.from.equals(((BusinessTicket)obj).from)
                && this.price == (((BusinessTicket)obj).price);
    }


}
