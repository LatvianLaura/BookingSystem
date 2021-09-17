


//program description
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class Booking {

    private static DBConnection booking1Db;

    public static void main(String[] args) {

        booking1Db = new DBConnection();


        int menuEntry = 10;
        String menuEntry0;
        Scanner scanner = new Scanner(System.in);

        do {
            // EXIT == 0
            System.out.println("********************");
            System.out.println("Welcome to table booking system!");
            System.out.println("********************");
            System.out.println("Please select :");
            System.out.println("1 - An owner of the restaurant");
            System.out.println("2 - A user");
            System.out.println("0 - Exit");
            System.out.println("********************");


            do {
                menuEntry0 = scanner.next();
                if (menuEntry0.matches("[0-2]")) {
                    menuEntry = Integer.parseInt(menuEntry0);
                    break;
                }
            } while (menuEntry0.matches("^[0-2]"));


            switch (menuEntry) {
                case 1:
//                  //an owner
                    String pin = null;
                    System.out.println("Please enter your pin code XXXX :");
                    pin = scanner.next();

                    if (pin.matches("1111")) {
                        int entry=0;
                        do {
                            System.out.println("************************************************************");
                            System.out.println("Hello Owner!!! Please select :");
                            System.out.println("1 - Show the list of tables ");
                            System.out.println("2 - Add a table ");
                            System.out.println("3 - Delete a table ");
                            System.out.println("4 - Show all reservations ");//clean old
                            System.out.println("0 - Exit");
                            System.out.println("************************************************************");
                            Tables newTable = new Tables();
                            //  entry = scanner.nextInt();//check that its integer

                            do {
                                menuEntry0 = scanner.next();
                                if (menuEntry0.matches("[0-4]")) {
                                    entry = Integer.parseInt(menuEntry0);
                                    break;
                                }
                            } while (menuEntry0.matches("^[0-4]"));

                            switch (entry) {
                                case 1:
                                    //SHOW A LIST OF TABLES
                                    boolean isPrintable = true;
                                    booking1Db.getTables(isPrintable);
                                    break;
                                case 2:
                                    // ADD A TABLE
                                    System.out.println("Please add number of seats for the table :");
                                    int numberOfSeats = 0;
                                    numberOfSeats = checkInputInt(numberOfSeats);
                                    newTable.setNumberOfSeats(numberOfSeats);
                                    booking1Db.createTable(newTable);
                                    System.out.println("The table with " + numberOfSeats + " seats was created!");
                                    break;
                                case 3:
                                    //DELETE A TABLE
                                    System.out.println("Please write ID of the table you want to delete:");
                                    int numberToDelete = scanner.nextInt();
                                    boolean wrongDateFormat = false;
                                    String currentDate = null;
                                    do {
                                        wrongDateFormat = false;
                                        currentDate = LocalDate.now().toString();
                                        try {
                                            LocalDate newCurrentDate = LocalDate.parse(LocalDate.now().toString());
                                        } catch (DateTimeParseException e) {
                                            wrongDateFormat = true;
                                            System.out.println("The input is invalid , please enter one more time. ");
                                        }
                                    }while (wrongDateFormat == true);
                                    //check future reservations
                                    ArrayList<Reservation> pendingReservations = new ArrayList<>();
                                    isPrintable = false;
                                    pendingReservations = booking1Db.getPendingReservations(numberToDelete, currentDate, isPrintable);
                                    if(pendingReservations.size() > 0){
                                        System.out.println("The table cannot be deleted, it has pending reservations:");
                                        isPrintable = true;
                                        pendingReservations = booking1Db.getPendingReservations(numberToDelete, currentDate, isPrintable);
                                    }else {
                                        booking1Db.deleteTable(numberToDelete);
                                        System.out.println("The table " + numberToDelete + " was deleted!");
                                    }
                                    break;
                                case 4: // SHOW ALL RESERVATIONS
                                    wrongDateFormat = false;
                                    currentDate = null;
                                    do {
                                        wrongDateFormat = false;
                                        currentDate = LocalDate.now().toString();
                                        try {
                                            LocalDate newCurrentDate = LocalDate.parse(currentDate);
                                        } catch (DateTimeParseException e) {
                                            wrongDateFormat = true;
                                            System.out.println("The input is invalid , please enter one more time. ");
                                        }
                                    }while (wrongDateFormat == true);
                                    ArrayList<Tables> allTables = new ArrayList<>();
                                    isPrintable = false;
                                    allTables = booking1Db.getTables(isPrintable);
                                    for (Tables table : allTables){
                                        ArrayList<Reservation> reservations = new ArrayList<>();
                                        System.out.println("----------------------------------------");
                                        System.out.print("Reservations for table " + table.getTableID() + " with " + table.getNumberOfSeats() + " seats are: ");
                                        isPrintable = true;
                                        reservations = booking1Db.getPendingReservations(table.getTableID(), currentDate, isPrintable);
                                    }
                                    break;
                                default:
//
                                    System.out.println("Menu item does not exist");

                            }
                        } while (entry != 0);
                    } else {
                        System.out.println("The pin is incorrect");
                        System.out.println();
                        break;
                    }
                    break;
                case 2:
                    // CUSTOMER PART
                    int userEntry=0;
                    do {
                        System.out.println();
                        System.out.println("Please select what do you want to do:");
                        System.out.println("1 - See available tables ");
                        System.out.println("2 - Book a table ");
                        System.out.println("0 - Exit");
                        System.out.println("************************************************************");


                        do {
                            menuEntry0 = scanner.next();
                            if (menuEntry0.matches("[0-2]")) {
                                userEntry = Integer.parseInt(menuEntry0);
                                break;
                            }
                        } while (menuEntry0.matches("^[0-2]"));



                        // userEntry = scanner.nextInt(); // check its int
                        switch (userEntry) {
                            case 1:
                                // SEE ALL AVAILABLE TABLES FOR SPECIFIC NUMBER OF PERSONS
                                String date = null;
                                String time = null;
                                int numberOfPersons = 0;
                                int reservationHours = 0;

                                //to check for the date input
                                boolean wrongDateFormat = false;
                                do {
                                    wrongDateFormat = false;
                                    System.out.println("Please select a date in format YYYY-MM-DD :");
                                    date = scanner.next();
                                    try {
                                        LocalDate newDate = LocalDate.parse(date);
                                    } catch (DateTimeParseException e) {
                                        wrongDateFormat = true;
                                        System.out.println("The input is invalid , please enter one more time. ");
                                    }
                                }while (wrongDateFormat == true);

                                System.out.println("How many persons are you : ");
                                numberOfPersons = 0;
                                numberOfPersons = checkInputInt(numberOfPersons); //scanner inside
                                ArrayList<Tables> availableTables = new ArrayList<>();
                                availableTables = booking1Db.getAvailableTables(numberOfPersons);

                                int tableId = 0;
                                ArrayList<Reservation> reservationsForTable = new ArrayList<>();
                                for (Tables table : availableTables) {
                                    tableId = table.getTableID();
                                    reservationsForTable = booking1Db.getAvailableReservations(tableId, date.toString(), table.getNumberOfSeats());
                                    if (reservationsForTable.size() == 0) {
                                        System.out.println("______________________________________________________________________");
                                        System.out.println("Table number " + table.getTableID() + " with " + table.getNumberOfSeats() + " number of seats has no reservations for this date");
                                    }
                                }

                                break;
                            case 2:
                                //BOOK A TABLE

                                System.out.println("Please enter your name: "); //  check that its a name - a-zA-Z
                                String name = scanner.next();
                                System.out.println("How many persons you are: "); //check that its a positive number, not 0
                                numberOfPersons = 0;
                                numberOfPersons = checkInputInt(numberOfPersons); //scanner insude
                                System.out.println("Please enter your phone number in format: XXXXXXXX: ");//empty line??????//check that it matches [0-9]*
                                String phoneNumber = null;
                                phoneNumber = checkInputPhoneNr(phoneNumber);

                                //date check - cannot be in the past
                                boolean wrongDateFormat1 = false;
                                boolean i = false;
                                wrongDateFormat = false;
                                do {
                                    do {
                                        wrongDateFormat = false;
                                        System.out.println("Please select a date in format YYYY-MM-DD :");
                                        date = scanner.next();
                                        try {
                                            LocalDate newDate = LocalDate.parse(date);
                                        } catch (DateTimeParseException e) {
                                            wrongDateFormat = true;
                                            System.out.println("The input is invalid , please enter one more time. ");
                                        }
                                    } while (wrongDateFormat == true);
                                    LocalDate newDate = LocalDate.parse(date);
                                    i = checkDatesFuture(newDate);
                                }while (i == true);
                                LocalDate newDate = LocalDate.parse(date);

                                System.out.println("The restaurant is working from 11:00 till 23:00.");
                                System.out.println("Please select a time in format HH:MM :");//check the format is correct, time more than 11:00 and less than 23:00

                                time = null;
                                time = checkInputTime(time); //time

                                LocalTime newTime = LocalTime.parse(time);

                                System.out.println("How many hours do you want your reservation to last? ");//check the input ans that time plus hours not more than 23:00,
                                reservationHours = scanner.nextInt();


                                System.out.println();
                                System.out.println("Please choose your table and enter table id");
                                availableTables = new ArrayList<>();
                                availableTables = booking1Db.getAvailableTables(numberOfPersons);
                                tableId = 0;
                                reservationsForTable = new ArrayList<>();
                                for (Tables table : availableTables) {
                                    tableId = table.getTableID();
                                    reservationsForTable = booking1Db.getAvailableReservations(tableId, date, table.getNumberOfSeats());
                                    if (reservationsForTable.size() == 0) {
                                        System.out.println("----------------------------------------");
                                        System.out.println("Table number " + table.getTableID() + " with " + table.getNumberOfSeats() + " number of seats has no reservations for this date");
                                    }
                                }

                                //check if this table exists
                                boolean tableExists = false;
                                int tableID = 0;
                                do {
                                    System.out.println("Please enter the table id, of the table you've chosen: ");
                                    tableID = scanner.nextInt();
                                    Tables newTable = booking1Db.tableExists(tableID);
                                    if (newTable.getTableID() == 0){
                                        System.out.println("There is no table with such Id. ");
                                    }else {
                                        tableExists = true;
                                    }
                                }while (!tableExists);


                                newTime = LocalTime.parse(time);
                                newDate = LocalDate.parse(date);

                                //check overlapping
                                ArrayList<Reservation> listOfReservations = new ArrayList<>();

                                listOfReservations = booking1Db.getAvailableReservationsNoPrinting(tableID, date, numberOfPersons);

                                if (!(booking1Db.reservationOverlappingCheck(listOfReservations, time, reservationHours))){
                                    continue;
                                }

                                // add a reservation to a table id
                                Person newPerson = new Person(); //creating a person that will be transferred to db
                                newPerson.setName(name);
                                newPerson.setTelephoneNumber(phoneNumber);
                                newPerson.setTableID(tableID);
                                newPerson.setNumberOfPersons(numberOfPersons);

                                booking1Db.createPerson(newPerson);

                                //add reservation
                                Reservation newReservation = new Reservation();
                                newReservation.setDate(newDate);
                                newReservation.setTime(newTime);
                                newReservation.setReservationHours(reservationHours);
                                newReservation.setTableID(tableID);
                                newReservation.setPersonTelephoneNumber(phoneNumber);

                                booking1Db.createReservation(newReservation);
                                System.out.println("Your reservation was recorded successfully!");
                                break;
                            default:
                                System.out.println("The input is invalid, please try again");
                        }
                    } while (userEntry != 0);
                    break;
                default:
                    if (menuEntry != 0) {// to check that the first input is not 0 - do while loop)
                        System.out.println("Menu item does not exist");
                    }
            }
        } while (menuEntry != 0);
    }

    private static int checkInputInt(int check1) {
        Scanner sc = new Scanner(System.in);
        String check0;
        check1 = 0;
        do {
            check0 = sc.next();
            if (check0.matches("^[1-9]\\d*$")) {
                check1 = Integer.parseInt(check0);
            } else {
                System.out.println("Input is not recognized, enter a number: ");
            }
        } while (!(check0.matches("^[1-9]\\d*$")));
        return check1;
    }

    private static String checkInputPhoneNr(String checkNr1) { //telephone number
        Scanner sc = new Scanner(System.in);
        do {
            checkNr1 = sc.next();
            if (checkNr1.matches("^\\d{8}$")) {
            } else {
                System.out.println("Input is not recognized, enter 8 numbers: ");
            }
        } while (!(checkNr1.matches("^\\d{8}$")));
        return checkNr1;
    }

    private static String checkInputTime (String time) {
        Scanner sc = new Scanner(System.in);

        do {
            time = sc.next();
            if (time.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            } else {
                System.out.println("Input is not recognized, enter time in format HH:MM : ");
            }
        } while (!(time.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")));
        return time;
    }

/*    private static boolean checkDatesFuture(LocalDate newDate0) {
        boolean i = false;
        GregorianCalendar gc = GregorianCalendar.from(newDate0.atStartOfDay(ZoneId.systemDefault()));
        Calendar d0 = new GregorianCalendar();
        LocalDate futureDate = LocalDate.now().plusMonths(6);
        GregorianCalendar todayAfter6Months = GregorianCalendar.from(futureDate.atStartOfDay(ZoneId.systemDefault()));
        if (gc.after(d0)) {
            i = false;
        }
        else if (!gc.after(d0)){
            i = true;
            System.out.println("Please enter a date after today " + LocalDate.now() + " and before " + futureDate + ": ");
        };
        return i;
    }*/

    private static boolean checkDatesFuture(LocalDate newDate0) {
        boolean i = false;
        LocalDate futureDate = LocalDate.now().plusMonths(6);
        if (newDate0.isAfter(LocalDate.now()) && newDate0.isBefore(futureDate)) {
            i = false;
        }
        else {
            i = true;
            System.out.println("Please enter a date after today " + LocalDate.now() + " and before " + futureDate + ": ");
        }
        return i;
    }

}