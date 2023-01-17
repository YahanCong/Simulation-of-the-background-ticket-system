package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlatformStaffTest {
    Employees staff;
    Destination dest = new Destination("Van");

    @BeforeEach
    public void setUp() {
        staff = new PlatformStaff("Amy");
        staff.setDest(dest);
    }

    @Test
    public void testGetter() {
        assertEquals("Amy",staff.getName());
        assertEquals(dest,staff.getDest());
    }
}
