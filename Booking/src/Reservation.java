import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
        private int ID;
        private LocalDate date;
        private LocalTime time;
        private int reservationHours;
        private int tableID;
        private String personTelephoneNumber;

        public Reservation() {
        }

        public Reservation(int ID, LocalDate date, LocalTime time, int reservationHours, int tableID, String personTelephoneNumber) {
                this.ID = ID;
                this.date = date;
                this.time = time;
                this.reservationHours = reservationHours;
                this.tableID = tableID;
                this.personTelephoneNumber = personTelephoneNumber;
        }

        @Override
        public String toString() {
                return "Reservation{" +
                        "ID=" + ID +
                        ", date=" + date +
                        ", time=" + time +
                        ", reservationHours=" + reservationHours +
                        ", tableID=" + tableID +
                        ", personTelephoneNumber='" + personTelephoneNumber + '\'' +
                        '}';
        }

        public int getID() {
                return ID;
        }

        public void setID(int ID) {
                this.ID = ID;
        }

        public LocalDate getDate() {
                return date;
        }

        public void setDate(LocalDate date) {
                this.date = date;
        }

        public LocalTime getTime() {
                return time;
        }

        public void setTime(LocalTime time) {
                this.time = time;
        }

        public int getReservationHours() {
                return reservationHours;
        }

        public void setReservationHours(int reservationHours) {
                this.reservationHours = reservationHours;
        }

        public int getTableID() {
                return tableID;
        }

        public void setTableID(int tableID) {
                this.tableID = tableID;
        }

        public String getPersonTelephoneNumber() {
                return personTelephoneNumber;
        }

        public void setPersonTelephoneNumber(String personTelephoneNumber) {
                this.personTelephoneNumber = personTelephoneNumber;
        }
}
