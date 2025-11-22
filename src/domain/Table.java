package domain;

import java.io.Serializable;

/**
 * Represents a physical table inside the restaurant.
 */
public class Table implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int seats;
    private boolean occupied;

    public Table(int id, int seats) {
        this.id = id;
        this.seats = seats;
        this.occupied = false;
    }

    public int getId() {
        return id;
    }

    public int getSeats() {
        return seats;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
