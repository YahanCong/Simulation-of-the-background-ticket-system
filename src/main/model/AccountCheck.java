package model;

public class AccountCheck {
    private static final String password = "1234abc";  //default administrator password


    //EFFECTS: validate if the entered password is true.
    public static boolean validatePassword(String pw) {
        return pw.equals(password);
    }


}
