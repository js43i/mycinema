package test;

public class Showtime {
    Movie movie;
    String hall;
    String time;
    double price;

    public Showtime() {
    }

    public Showtime(Movie movie, String time, String hall) {
        this.movie = movie;
        this.time = time;
        this.hall = hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}