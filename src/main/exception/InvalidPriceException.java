package exception;

import model.Ticket;

public class InvalidPriceException extends InvalidInputException {
    public String outPutPriceMessage = "Ticket Price you entered must in valid range: First Class: 500-10000; "
            + "Economic Class 10-1000; Business Class: 100-2000";

    public InvalidPriceException(String message) {
        super(message);
        message = outPutPriceMessage;
        System.out.println(message);
    }

    public String getOutPutPriceMessage() {
        return outPutPriceMessage;
    }
}
