package domain;

public class Table {
    private final int id;
    private final int seats;
    private boolean occupied;

    public Table(int id, int seats) {
        this.id = id;
        this.seats = seats;
        this.occupied = false;
    }

    public int getId() { return id; }
    public int getSeats() { return seats; }
    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }

    @Override
    public String toString() {
        return id + " -> Seats: " + seats + " | Occupied: " + occupied;
    }
}
