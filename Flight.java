/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Incomplete
 * Last update: 11/27/15
 * Submitted:  12/07/15
 * Comment:
 * @author: Timothy Baker
 * @version: 1.0
 */

public class Flight {
        private String flightNumber; //this flight's current flight number
        private String destination; //this flight's geographical destination

        //default constructor
        public Flight() {
        }

        //full argument constructor
        public Flight(String flightNumber, String destination) {
                this.flightNumber = flightNumber;
                this.destination = destination;
        }

        //toString method
        public String toString() {
                return "Flight " + flightNumber + " to " + destination;
        }
        //getters below
        public String getFlightNumber() {
                return flightNumber;
        }

        public String getDestination() {
                return destination;
        }
}
