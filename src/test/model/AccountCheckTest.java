package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountCheckTest {
    private AccountCheck testAccount = new AccountCheck();

    @Test
    public void validPasswordTest() {
        assertTrue(testAccount.validatePassword("1234abc"));
        assertFalse(testAccount.validatePassword("2345ygfdcs"));
    }
}
