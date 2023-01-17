package model;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employees {
    private List<Employees> underlingList;
    private Destination dest;
    private ArrayList<String> underlingNameList;

    public Manager(String name) {
        super(name);
        dest = null;
        underlingList = new ArrayList<>();
        underlingNameList = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: add a new employee if it is not exist in employees list
    public void addEmployees(Employees e) {
        if (!underlingList.contains(e)) {
            underlingList.add(e);
        }
    }

    //MODIFIES: this, employee
    //EFFECTS: set a new destination for entered employee
    public void assignedDestForStaff(Employees e, Destination dest) {
        e.setDest(dest);
    }

    public List<Employees> getUnderlingList() {
        return underlingList;
    }


    public Destination getDest() {
        return dest;
    }

    //MODIFIES: this
    //EFFECTS: get all underlyings' names in this manager
    public ArrayList<String> getAllEmployeeNamesAsList() {
        for (Employees e : underlingList) {
            String employeeName = e.getName();
            underlingNameList.add(employeeName);
        }
        return underlingNameList;
    }


    //EFFECTS: if the employee is exist in this manager, get it. Otherwise not.
    public Employees getAnEmployeeWithName(String name) {
        Employees getE = null;
        for (Employees e: underlingList) {
            if (e.getName().equals(name)) {
                getE = e;
            }
        }
        return getE;
    }


    //EFFECTS: if the employee name is exist in this manager, get it. Otherwise not.
    public String getAllEmployeeNamesAsString() {
        String allEmployeesName = "";
        for (Employees employee : underlingList) {
            allEmployeesName = allEmployeesName + employee.getName() + "\n";
        }
        return allEmployeesName;
    }


}
