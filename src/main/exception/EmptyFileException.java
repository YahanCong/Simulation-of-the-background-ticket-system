package exception;

public class EmptyFileException extends Exception {
    public String outputMessage = "EmptyFile";

    public EmptyFileException(String message) {
        super(message);
        message = outputMessage;
        System.out.println(message);

    }

}
