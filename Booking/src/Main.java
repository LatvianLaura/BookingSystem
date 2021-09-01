import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    // LocalDate someday2 = LocalDate.of(2021, Month.JANUARY, 31)
    //Restaurant restaurant1 = new Restaurant(1);
    //Tables table1 = new Tables(1, 2, );

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        try {

            String dbUrl = "jdbc:sqlite:booking.db";
            Connection conn = DriverManager.getConnection(dbUrl);

            if( conn != null ) {
                DatabaseMetaData databaseMetadata = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Connected to " + databaseMetadata.getDatabaseProductName() + " " + databaseMetadata.getDatabaseProductVersion());

                // CREATING A TABLE
                Statement statement = conn.createStatement();
                String sqlStatement =
                        "CREATE TABLE IF NOT EXISTS customers "+
                                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "name TEXT NOT NULL, " +
                                "number_of_persons INTEGER NOT NULL, " +
                                "telephone_number TEXT NOT NULL, " +
                                "table_id INTEGER, " +
                                "reservation_id INTEGER ) ";
                                //"FOREIGN KEY(shop_id) REFERENCES shops(id) )";
                statement.execute(sqlStatement);


                System.out.println("Please enter your name: ");
                String userInput1 = scan.next();

                System.out.println("Please enter the number of persons: ");
                int userInput2 = scan.nextInt();

                System.out.println("Please enter your telephone number : ");
                String userInput3 = scan.next();



                // INSERT FIRST ROW
                sqlStatement = "INSERT INTO customers (name, number_of_persons, telephone_number )" +
                        "values ( '" +  userInput1 + "', " +  userInput2 + ", '" +  userInput3 + "' )";
                // how to add integer?
                statement.execute(sqlStatement);

                //DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MMM-dd");
                //LocalDate lDate = new LocalDate().parse("2005-nov-12",format);

                /*
                sqlStatement = "SELECT * FROM customers";
                ResultSet resultSet = statement.executeQuery(sqlStatement);

                while ( resultSet.next() ) {
                    String productName = resultSet.getString("name");
                    String expiryDate = resultSet.getString("expiry_date");
                    int id = resultSet.getInt("id");

                    System.out.println("ID:" + id + " name: " + productName + " ExpiryDate: " + expiryDate);
                }



                sqlStatement = "DELETE FROM groceries" +
                        " WHERE id = 2";
                statement.execute(sqlStatement);
                */

                sqlStatement =
                        "CREATE TABLE IF NOT EXISTS tables"+
                                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "number_of_seats INTEGER NOT NULL ) ";
                statement.execute(sqlStatement);


                sqlStatement = "SELECT shops.name as sname, groceries.name as gname, groceries.expiry_date " +
                        "FROM shops LEFT JOIN groceries " +
                        "ON groceries.shop_id = shops.id";

                resultSet = statement.executeQuery(sqlStatement);

                while ( resultSet.next() ) {
                    String shopName = resultSet.getString("sname");
                    String productName = resultSet.getString("gname");
                    String expiryDate = resultSet.getString("expiry_date");

                    System.out.println("SHOP:" + shopName + " name: " + productName + " ExpiryDate: " + expiryDate);
                }

            }



        } catch( SQLException exception ) {
            System.out.println("An error has occured" + exception.toString());
        }



    }
}