package model;

import java.util.ArrayList;

public class ManagerManagement {
    private ArrayList<Manager> managerList;
    private ArrayList<String> managerNameList;

    public ManagerManagement() {
        managerList = new ArrayList<>();
        managerNameList = new ArrayList<>();
    }

    public ArrayList<Manager> getManagerList() {
        return managerList;
    }



    //MODIFIES: this
    //EFFECTS: get a string list of managers name;
    public ArrayList<String> getManagerNameList() {
        for (Manager manager : managerList) {
            String managerName = manager.getName();
            if (!managerNameList.contains(managerName)) {
                managerNameList.add(managerName);
            }
        }
        return managerNameList;
    }

    //MODIFIES:this
    //EFFECTS: if the enter name does not exist in manager name list, add it in, otherwise not.
    public void addManagerWithName(String name) {
        if (!managerNameList.contains(name)) {
            Manager newManager = new Manager(name);
            addManager(newManager);
        }
    }

    //MODIFIES: this
    //EFFECTS: if the enter manager does not exist in manager list, add it in, otherwise not.
    public void addManager(Manager newManager) {
        if (!managerList.contains(newManager)) {
            managerList.add(newManager);
        }
    }

    //MODIFIES: this
    //EFFECTS: get a manager if its name is exist in system
    public Manager getManagerWithName(String name) {
        Manager getManager = null;
        for (Manager m : managerList) {
            if (m.getName().equals(name)) {
                getManager = m;
            }
        }
        return getManager;
    }

}
