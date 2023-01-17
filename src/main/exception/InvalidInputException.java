package exception;


public class InvalidInputException extends RuntimeException {
    public String outputMessage = "Please recheck your input";

    public InvalidInputException(String message) {
        super(message);
        message = outputMessage;
        System.out.println(message);

    }



}
