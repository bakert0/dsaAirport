/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update: 11/27/15
 * Submitted:  12/07/15
 * Comment: Setting things to null in constructor is not necessary but done for clarity sake.
 * @author: Timothy Baker
 * @version: 1.0
 */

public class Runway {
        private String name; // the name of the runway *must be unique
        private QueueArrayBased<Flight> flights; // the queue of planes waiting to use this runway
        private ListArrayBasedPlus<Flight> waitingToReEnter; //the list of planes waiting to re-enter this runway

        //single argument constructor used to initial a runway that has no waiting planes yet

        public Runway(String name) {
                this.name = name;
                this.flights = new QueueArrayBased<Flight>();
                this.waitingToReEnter = new ListArrayBasedPlus<Flight>();
        }

        //full argument constructor for when all information about a runway is already known
        public Runway(String name, QueueArrayBased<Flight> flights, ListArrayBasedPlus<Flight> waitingToReEnter) throws AirportException{
                this.name = name;
                this.flights = flights;
                this.waitingToReEnter = waitingToReEnter;
        }

        //adds a flight to this runway's queue
        public void addFlight(Flight flight) {
                flights.enqueue(flight);
        }

        //adds a flight to the list of planes waiting to re-enter this runway
        public void addWaitingToReEnterFlight(Flight flight) {
                waitingToReEnter.add(waitingToReEnter.size(), flight);
        }

        //gets the next flight from this runway's queue and removes it from the queue
        public Flight getNextFlight() {
                return flights.dequeue();
        }

        //checks to see if this runway has any queued flights
        public boolean hasFlights() {
                return flights.isEmpty() == false;
        }

        //checks to see if this runway has any planes waiting to re enter
        public boolean hasWaitingToReEneter() {
                return waitingToReEnter.isEmpty() == false;
        }

        //toStrings below

        //general toString for this runway. Runways can be identified by their name.
        public String toString() {
                return name;
        }

        //this one is for displaying the flight queue line by line
        public String toStringFlights() {
                return flights.toString().replaceAll(" Flight", "\nFlight");
        }

        //getters below

        public String getName() {
                return name;
        }

        public QueueArrayBased<Flight> getFlights() {
                return flights;
        }

        public ListArrayBased<Flight> getWaitingToReEnter() {
                return waitingToReEnter;
        }
}
