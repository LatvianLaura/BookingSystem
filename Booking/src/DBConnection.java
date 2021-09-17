
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DBConnection {

    private Connection conn;

    public DBConnection() { //creates 3 tables: tables, persons and reservations
        try {

            String dbUrl = "jdbc:sqlite:bookings.db";
            conn = DriverManager.getConnection(dbUrl);
            if (conn != null) {
                Statement statement = conn.createStatement();
                String sqlStatement =
                        "CREATE TABLE IF NOT EXISTS tables " +
                                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "number_of_seats INTEGER NOT NULL) ";

                statement.execute(sqlStatement);


                sqlStatement =
                        "CREATE TABLE IF NOT EXISTS reservations " +
                                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "date TEXT, " +
                                "time TEXT, " +
                                "reservation_duration INTEGER , " +
                                "table_id INTEGER , " +
                                "person_phone_number TEXT , " +
                                "FOREIGN KEY(table_id) REFERENCES tables(id) , " +
                                "FOREIGN KEY(person_phone_number) REFERENCES persons(telephone_number)) ";
                statement.execute(sqlStatement);


                sqlStatement =
                        "CREATE TABLE IF NOT EXISTS persons " +
                                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "name TEXT, " +
                                "telephone_number TEXT, " +
                                "number_of_persons INTEGER NOT NULL, " +
                                "table_id INTEGER , " +
                                "FOREIGN KEY(table_id) REFERENCES tables(id))";

                statement.execute(sqlStatement);

                System.out.println("DB created");
            }
        } catch (SQLException exception) {
            System.out.println("Database issues: " + exception);
        }
    }

    //TABLE
    public void createTable(Tables table) {

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "INSERT INTO tables (" +
                    "number_of_seats) " +
                    "VALUES (" +
                    "" + table.getNumberOfSeats() + ")";
            statement.execute(sqlStatement);

        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }
    }

    public ArrayList<Tables> getTables(boolean isPrintable) {
        //prints out all tables from database, returns ArrayList of tables
        ArrayList<Tables> tableList = new ArrayList<Tables>();

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "SELECT * FROM tables";

            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()) {

                // Creates new Tables object + adds all the tables to array
                Tables table = new Tables();
                table.setTableID(rs.getInt("id"));
                table.setNumberOfSeats(rs.getInt("number_of_seats"));
                tableList.add(table);
                if(isPrintable) {
                    System.out.println(table.toString());
                }
            }

        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }
        return tableList;
    }


    public void deleteTable(int tableIDtoDelete) {
        //deletes a table from database using table_id
        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "DELETE FROM tables WHERE (id = " + tableIDtoDelete + ")";
            statement.execute(sqlStatement);

        } catch (SQLException exception) {
            System.out.println("Error : " + exception);
        }
    }

    public ArrayList<Tables> getAvailableTables(int numberOfPersons) {
        //returns all tables with amount of seats more or equal to amount of persons,
        // returns an array of tables

        ArrayList<Tables> tableList = new ArrayList<Tables>();

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "SELECT * FROM tables WHERE (number_of_seats >= " + numberOfPersons + ")";

            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()) {

                // Create new Tables object + add  the tables
                Tables table = new Tables();
                table.setTableID(rs.getInt("id"));
                table.setNumberOfSeats(rs.getInt("number_of_seats"));
                tableList.add(table);
                //System.out.println(table.toString());
            }

        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }

        return tableList;
    }



    public Tables tableExists(int tableId) {

        ArrayList<Tables> tableList = new ArrayList<Tables>();
        Tables table = new Tables();
        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "SELECT * FROM tables WHERE (id = " + tableId + ")";

            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()) {

                table.setTableID(rs.getInt("id"));
                table.setNumberOfSeats(rs.getInt("number_of_seats"));
                //System.out.println(table.toString());
            }

        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }

        return table ;
    }

    //PERSON
    public void createPerson(Person person) { //creates a record in db-persons

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "INSERT INTO persons (" +
                    "name, telephone_number, number_of_persons, table_id) " +
                    "VALUES (" +
                    "'" + person.getName() + "'," +
                    "'" + person.getTelephoneNumber() + "'," +
                    "" + person.getNumberOfPersons() + ", " +
                    "" + person.getTableID() + ")";

            statement.execute(sqlStatement);

        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }
    }
    //RESERVATION
    public void createReservation(Reservation reservation) { //creates a row in db-reservations
        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "INSERT INTO reservations (" +
                    "date, time, reservation_duration, table_id, person_phone_number) " +
                    "VALUES (" +
                    "'" + reservation.getDate() + "'," +
                    "'" + reservation.getTime() + "'," +
                    "'" + reservation.getReservationHours() + "'," +
                    "" + reservation.getTableID() + ", " +
                    "'" + reservation.getPersonTelephoneNumber() + "')";

            statement.execute(sqlStatement);

        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }
    }

    public ArrayList<Reservation> getAvailableReservations (int tableId, String date, int numberOfSeats) { //prints all reservations for specific table and date

        ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

        try {

            Statement statement = conn.createStatement();
            //order by time
            String sqlStatement = "SELECT * FROM reservations WHERE ((table_id = " + tableId + " ) AND (date = '" + date + "')) ORDER BY time ASC";

            ResultSet rs = statement.executeQuery(sqlStatement);
            String newTime= null;
            int counter = 0;//for printing message only one time
            while (rs.next()) {
                if (counter == 0){
                    System.out.println("----------------------------------------");
                    System.out.println("Table number " + tableId + " with " + numberOfSeats + " number of seats for date " + date + " has such reservations: ");
                }
                counter++; //becomes 1 and more, so it wont print the message above any more
                //creates reservation for printing
                Reservation reservation = new Reservation();
                newTime = rs.getString("time");
                reservation.setTime(LocalTime.parse(newTime));
                reservation.setReservationHours(rs.getInt("reservation_duration"));
                reservationList.add(reservation);
                int till = 0; //end time
                till = reservation.getTime().getHour() + reservation.getReservationHours();

                System.out.println("From " + reservation.getTime() + " till  "  + till + ":00. ");
            }

        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }
        return reservationList;
    }


    public ArrayList<Reservation> getPendingReservations (int tableId, String date, boolean isPrintable) {
        //prints all reservations for specific table and after (including) the date

        ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

        try {

            Statement statement = conn.createStatement();
            String sqlStatement = "SELECT * FROM reservations WHERE ((table_id = " + tableId + " ) AND (date >= '" + date + "')) ORDER BY date , time ASC";

            ResultSet rs = statement.executeQuery(sqlStatement);
            String time= null;
            int counter= 0;
            while (rs.next()) {

                //creates reservation for printing
                Reservation reservation = new Reservation();
                //////// time = rs.getString("time");
                reservation.setTime(LocalTime.parse(rs.getString("time")));
                reservation.setReservationHours(rs.getInt("reservation_duration"));
                reservation.setDate(LocalDate.parse(rs.getString("date")));
                reservationList.add(reservation); //adds reservation to array

                int till = reservation.getTime().getHour() + reservation.getReservationHours(); //end time
                //reservationList.add(reservation);
                if (counter == 0){ //to print from the next line - more beautiful code
                    System.out.println();
                }
                if (isPrintable) {
                    System.out.println("For " + reservation.getDate() + " From " + reservation.getTime() + " till  " + till + ":00. ");
                }
                counter++;


            }
            System.out.println();
            if(counter == 0) {
                System.out.println("NONE");
            }
        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }
        return reservationList;
    }


    public boolean reservationOverlappingCheck (ArrayList<Reservation> listOfReservations, String time, int reservationHours){ ///???????
        int startTime = LocalTime.parse(time).getHour();
        //System.out.println("start time " + startTime);
        int endTime = startTime + reservationHours;

        for (Reservation reservation: listOfReservations){
            int startReservationTime = reservation.getTime().getHour();
            //System.out.println("end time " + startReservationTime);
            int endReservationTime = reservation.getReservationHours()+ startReservationTime;
            // System.out.println("end time " + endReservationTime);
            if((startReservationTime < startTime) && (endReservationTime > startTime)  || (startReservationTime >= startTime && endReservationTime <= endTime) || (startReservationTime < endTime && endReservationTime > endTime)){
                System.out.println("This time is overlapping with reservation : [" + startReservationTime + ":00 - " + endReservationTime + ":00] for this table.");
                System.out.println("Please book another table, or choose another time");
                System.out.println("************************************************************");
                return false;
            }
        }
        return true;
    }
    public ArrayList<Reservation> getAvailableReservationsNoPrinting (int tableId, String date, int numberOfSeats) { //prints all reservations for specific table and date

        ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

        try {

            Statement statement = conn.createStatement();

            String sqlStatement = "SELECT * FROM reservations WHERE ((table_id = " + tableId + " ) AND (date = '" + date + "')) ORDER BY time ASC";

            ResultSet rs = statement.executeQuery(sqlStatement);
            String newTime= null;
            //int counter = 0;//for printing message only one time
            while (rs.next()) {

                //creates reservation for printing
                Reservation reservation = new Reservation();
                newTime = rs.getString("time");
                reservation.setTime(LocalTime.parse(newTime));
                reservation.setReservationHours(rs.getInt("reservation_duration"));
                reservationList.add(reservation);
                int till = 0;
                till = reservation.getTime().getHour() + reservation.getReservationHours();


                // System.out.println("From " + reservation.getTime() + " till  "  + till + ":00. ");
                //System.out.println("It will last till " + tillH + ":00");
            }

        } catch (SQLException exception) {
            System.out.println("Error: " + exception);
        }
        return reservationList;
    }


}