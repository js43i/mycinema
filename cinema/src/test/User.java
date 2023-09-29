package test;

import java.util.ArrayList;
import java.util.List;

public class User {
    List<Ticket> purchasedTickets = new ArrayList<>();


    public void addPurchasedTicket(Ticket ticket) {
        purchasedTickets.add(ticket);
    }

    public List<Ticket> getPurchasedTickets() {
        return purchasedTickets;
    }

    String userID;
    String username;
    String level;

    public User() {
    }

    public User(String userID, String username, String level) {
        this.userID = userID;
        this.username = username;
        this.level = level;
    }

    public void setPurchasedTickets(List<Ticket> purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}