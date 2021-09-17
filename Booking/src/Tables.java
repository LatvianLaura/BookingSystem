import java.util.ArrayList;

public class Tables {
    private int tableID;
    private int numberOfSeats;
    //private ArrayList<Reservation> reservationForTable = new ArrayList<>();


    public Tables() {
    }

    public Tables(int tableID, int numberOfSeats) {
        this.tableID = tableID;
        this.numberOfSeats = numberOfSeats;
    }


    @Override
    public String toString() {
        return "Table {" +
                "ID = " + tableID +
                " with " + numberOfSeats + " seats }";
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

    /*public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }*/

    /*public ArrayList<Reservation> getReservationForTable() {
        return reservationForTable;
    }

    public void setReservationForTable(ArrayList<Reservation> reservationForTable) {
        this.reservationForTable = reservationForTable;
    }*/
}
