import java.util.ArrayList;

public class Restaurant {
    private int restaurantID;
    private ArrayList<Tables> arrayOfTables = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(int restaurantID, ArrayList<Tables> arrayOfTables) {
        this.restaurantID = restaurantID;
        this.arrayOfTables = arrayOfTables;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public ArrayList<Tables> getArrayOfTables() {
        return arrayOfTables;
    }

    public void setArrayOfTables(ArrayList<Tables> arrayOfTables) {
        this.arrayOfTables = arrayOfTables;
    }
}
