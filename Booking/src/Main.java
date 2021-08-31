import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    // Function to add x in arr
    public static boolean addX(Tables ArrayOfTables, Tables table) {
        // create a new ArrayList
        ArrayList<Tables> arrlist = new ArrayList<Tables>(
                Arrays.asList(ArrayOfTables)); // Artjoms???

        // Add the new element
        arrlist.add(table);

        // Convert the Arraylist to array  - Artjoms????
        ArrayOfTables = arrlist.toArray(table);

        // return the array
        return ArrayOfTables;
    }

    // Function to remove a hero from an array
    public static String[] removeX(String heroArray[], int heroIndexToDelete) {
        // create a new ArrayList
        List<String> arrlist
                = new ArrayList<String>(
                Arrays.asList(heroArray));

        // Find and remove a String from an array
        arrlist.remove(heroIndexToDelete);

        // Convert the Arraylist to array
        heroArray = arrlist.toArray(heroArray);
        heroArray = Arrays.copyOf(heroArray, heroArray.length - 1);
        // return the array
        return heroArray;
    }

    // LocalDate someday2 = LocalDate.of(2021, Month.JANUARY, 31)
    Restaurant restaurant1 = new Restaurant(1);
    Tables table1 = new Tables(1, 2, );

    public static void main(String[] args) {

    }
}