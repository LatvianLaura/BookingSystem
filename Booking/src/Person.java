public class Person {
        private int ID;
        private String name;
        private String telephoneNumber;
        private int numberOfPersons;
        private int tableID;


        public Person() {
        }

        public Person(int ID, String name, String telephoneNumber, int numberOfPersons, int tableID) {
                this.ID = ID;
                this.name = name;
                this.telephoneNumber = telephoneNumber;
                this.numberOfPersons = numberOfPersons;
                this.tableID = tableID;

        }

        @Override
        public String toString() {
                return "Person{" +
                        "ID=" + ID +
                        ", name='" + name + '\'' +
                        ", telephoneNumber='" + telephoneNumber + '\'' +
                        ", numberOfPersons=" + numberOfPersons +
                        ", tableID=" + tableID +
                        '}';
        }

        public int getID() {
                return ID;
        }

        public void setID(int ID) {
                this.ID = ID;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getTelephoneNumber() {
                return telephoneNumber;
        }

        public void setTelephoneNumber(String telephoneNumber) {
                this.telephoneNumber = telephoneNumber;
        }

        public int getNumberOfPersons() {
                return numberOfPersons;
        }

        public void setNumberOfPersons(int numberOfPersons) {
                this.numberOfPersons = numberOfPersons;
        }

        public int getTableID() {
                return tableID;
        }

        public void setTableID(int tableID) {
                this.tableID = tableID;
        }

}
