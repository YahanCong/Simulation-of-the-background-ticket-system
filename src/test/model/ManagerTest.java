package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ManagerTest {
    private Manager Tim;
    private Employees Ivy;
    private Employees Frank;
    private Destination Van;
    private Destination Jas;

    @BeforeEach
    public void setUp() {
        Tim = new Manager("Tim");
        Ivy = new PlatformStaff("Ivy");
        Frank = new PlatformStaff("Frank");
        Van = new Destination("Vancouver");
        Jas = new Destination("Jasper");

        Tim.addEmployees(Ivy);
        Ivy.setDest(Van);
    }

    @Test
    public void testAddEmployees() {
        ArrayList<Employees> testList = new ArrayList<>();
        testList.add(Ivy);
        Tim.addEmployees(Ivy);
        assertEquals(testList, Tim.getUnderlingList());
        Tim.addEmployees(Frank);
        testList.add(Frank);
        assertEquals(testList,Tim.getUnderlingList());
    }

    @Test
    public void testAssignedDestForStaff() {
        Tim.assignedDestForStaff(Ivy, Jas);
        assertEquals(Jas,Ivy.getDest());
    }

    @Test
    public void testGetAnEmployeeWithName() {
        assertEquals(Ivy,Tim.getAnEmployeeWithName("Ivy"));
        assertEquals(null, Tim.getAnEmployeeWithName("Ace"));
    }

    @Test
    public void testGetAllEmployeeNames() {
        ArrayList<String> employeesList = new ArrayList<>();
        Tim.addEmployees(Frank);
        employeesList.add("Ivy");
        employeesList.add("Frank");
        assertEquals(Tim.getAllEmployeeNamesAsList(),employeesList);
        assertEquals(Tim.getAllEmployeeNamesAsString(),"Ivy\n" +
                "Frank" + "\n");
    }
}
