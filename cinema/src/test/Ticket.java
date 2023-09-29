package test;

import java.util.Date;

public class Ticket {
    String id;
    double count;
    Date time;

    public Ticket(String id, double count, Date time) {
        this.id = id;
        this.count = count;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}