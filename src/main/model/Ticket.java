package model;


public abstract class Ticket {
    //field
    String from;
    Destination dest;
    double price;

    //constructor

    public Ticket(String start, Destination dest, double price) {
        this.from = start;
        this.dest = dest;
        this.price = price;
    }

    //method
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Destination getDest() {
        return dest;
    }

    public void setDest(Destination dest) {
        if (!dest.equals(this.dest)) {
            this.dest = dest;
            dest.addTicket(this);
        }

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return String.format("%d %s %s %f\n", getTypeNum(), from, dest.getName(), price);
    }



    public abstract int getTypeNum();

    public abstract boolean checkPriceInRange();







}
