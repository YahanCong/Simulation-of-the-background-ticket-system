package model;

public abstract class Employees {
    private String name;
    private Destination dest;

    public Employees(String name) {
        this.name = name;
        dest = null;

    }

    public String getName() {
        return name;
    }

    public Destination getDest() {
        return dest;
    }

    public void setDest(Destination dest) {
        this.dest = dest;
    }
}
