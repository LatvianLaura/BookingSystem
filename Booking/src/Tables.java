import java.util.ArrayList;

public class Tables {
    private int tableID;
    private int numberOfSeats;
    private int restaurantID;
    private ArrayList<Reservation> reservationForTable = new ArrayList<>();


    public Tables() {
    }

    public Tables(int tableID, int numberOfSeats, int restaurantID, ArrayList<Reservation> reservationForTable) {
        this.tableID = tableID;
        this.numberOfSeats = numberOfSeats;
        this.restaurantID = restaurantID;
        this.reservationForTable = reservationForTable;
    }
    public boolean addNewReservation(Reservation reservation) {
        return reservationForTable.add(reservation);
    }
    public Reservation removeReservation(int index) {
        return reservationForTable.remove(index);
    }

    @Override
    public String toString() {
        return "Tables{" +
                "tableID=" + tableID +
                ", numberOfSeats=" + numberOfSeats +
                ", restaurantID=" + restaurantID +
                ", reservationForTable=" + reservationForTable +
                '}';
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public ArrayList<Reservation> getReservationForTable() {
        return reservationForTable;
    }

    public void setReservationForTable(ArrayList<Reservation> reservationForTable) {
        this.reservationForTable = reservationForTable;
    }
}
