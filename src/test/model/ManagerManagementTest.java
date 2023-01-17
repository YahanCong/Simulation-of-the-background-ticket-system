package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerManagementTest {
    ManagerManagement managerManagement;
    Manager Tim;
    Manager Dean;
    Manager Sally;

    @BeforeEach
    public void setUp() {
        managerManagement = new ManagerManagement();
        Tim = new Manager("Tim");
        Dean = new Manager("Dean");

        managerManagement.addManager(Tim);
    }

    @Test
    public void testAddManagers() {
        managerManagement.addManager(Tim);
        ArrayList<Manager> managers = new ArrayList<>();
        ArrayList<String> managerNames = new ArrayList<>();
        managers.add(Tim);
        managerNames.add("Tim");
        assertEquals(managerManagement.getManagerList(),managers);
        assertEquals(managerManagement.getManagerNameList(),managerNames);
        managerManagement.addManager(Dean);
        managers.add(Dean);
        managerNames.add("Dean");
        assertEquals(managers,managerManagement.getManagerList());
        assertEquals(managerNames,managerManagement.getManagerNameList());
    }

    @Test
    public void testAddManagerWithName() {
        managerManagement.addManagerWithName("Ace");
        assertTrue(managerManagement.getManagerNameList().contains("Ace"));
    }

    @Test
    public void testGetManagerWithName(){
        assertEquals(Tim,managerManagement.getManagerWithName("Tim"));
        assertEquals(null,managerManagement.getManagerWithName("Black"));
    }
}
