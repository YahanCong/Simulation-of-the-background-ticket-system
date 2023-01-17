package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeUI {
    Scanner scanner;
    public ManagerManagement managerManagement;

    public EmployeeUI() {
        scanner = new Scanner(System.in);
        managerManagement = new ManagerManagement();

        Manager dean = new Manager("Dean");
        Manager frank = new Manager("Frank");
        Employees tim = new PlatformStaff("Tim");
        Employees sam = new PlatformStaff("Sam");
        Employees amy = new PlatformStaff("Amy");
        managerManagement.addManager(dean);
        managerManagement.addManager(frank);
        dean.addEmployees(tim);
        dean.addEmployees(sam);


    }


    //EFFECTS: get information from bookingSystem and identify manager's name.
    public void getStart(Scanner scanner, BookingSystem bookingSystem) {
        System.out.println("Please enter your name");
        String verifiedName = scanner.next();
        while (true) {
            if (managerVerifiedWithName(verifiedName)) {
                Manager manager = managerManagement.getManagerWithName(verifiedName);
                System.out.println("Welcome, Manager " + verifiedName);
                nextOrderChoose(scanner, manager,bookingSystem);
                return;
            } else if (verifiedName.equals("quit")) {
                return;
            } else {
                System.out.println("Please enter a correct name.");
                verifiedName = scanner.next();
            }
        }

    }

    //REQUIRES: the entered integer should be one of {0,1,2}
    //EFFECTS: choose the next order of what manager prefer to do.
    private void nextOrderChoose(Scanner scanner, Manager manager, BookingSystem bookingSystem) {
        while (true) {
            System.out.println("Please choose your next order: 1. assigned/reset a new dest for your staff. "
                    + "2. print all names of your underlying 0. quit manager system");
            int choose = scanner.nextInt();
            if (choose == 1) {
                assignedNewDestForStaff(scanner, manager,bookingSystem);
            } else if (choose == 2) {
                System.out.println(manager.getAllEmployeeNamesAsString());
            } else if (choose == 0) {
                System.out.println("Thank you for using manager system");
                return;
            }


        }
    }

    //MODIFIES: platform, manager
    //EFFECTS: if the input manager has the input platform's name in his list, change this platform's
    //         represented destination
    private void assignedNewDestForStaff(Scanner scanner, Manager manager, BookingSystem bookingSystem) {
        System.out.println("Please enter your staff's name");
        String staffName = scanner.next();
        if (manager.getAllEmployeeNamesAsList().contains(staffName)) {
            assignedNewDestWithDestName(scanner, manager, bookingSystem, staffName);
            System.out.println("Your staff's dest has changed successfully");
            return;
        } else {
            System.out.println("The entered name is not exist in your underlying.");
        }
    }

    //REQUIRES:
    //MODIFIES: platform, manager
    //EFFECTS: change a platform's represented destination into a new one if this new destination is exist in system.
    private void assignedNewDestWithDestName(Scanner sc, Manager m, BookingSystem bs, String sn) {
        Employees e;
        e = m.getAnEmployeeWithName(sn);
        System.out.println("Please enter destination name");
        String newDestName = sc.next();
        Destination newdest;
        if (bs.getDests().get(newDestName) == null) {
            System.out.println("The entered destination is not exist, please enter a correct destination");
        } else {
            newdest = bs.getDests().get(newDestName);
            m.assignedDestForStaff(e,newdest);
            return;
        }
    }

    //EFFECTS: verified whether the manager's name is in system or not.
    private boolean managerVerifiedWithName(String verifiedName) {
        ArrayList<String> managerNameList = managerManagement.getManagerNameList();
        if (managerNameList.contains(verifiedName)) {
            return true;
        } else {
            return false;
        }
    }


}
