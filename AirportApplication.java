/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Incomplete
 * Last update: 11/27/15
 * Submitted:  12/07/15
 * Comment:
 * @author: Timothy Baker
 * @version: 1.0
 */

import java.io.*;
public class AirportApplication {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //used to read user input

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        ListArrayBasedPlus<Runway> runways = new ListArrayBasedPlus<Runway>(); //runways is the current list of active runways at the airport
        getInitialRunways(runways);

        int currentRunway = 0; //used to keep track of which runways turn it is. *remember round robin fashion*

        do { //menu loop
            System.out.println("Select from the following menu:");
            printMenu();
            System.out.print("Make your selection now: ");
            int command = Integer.parseInt(reader.readLine().trim());
            System.out.println(command); // feedback for output file


            //Lets try to make every option its own method unelss its a one or two liner.
            //Completed Options: 1, 6, 9
            //Incomplete: 2, 3, 4, 5, 7, 8
            //Need to add conditionals for when all the runways are empty (where applicable)
            switch(command) {
            case 9:
                exit = true;
                System.out.println("Exiting program...Good Bye");
                break;
            case 1:
                addFlight(runways);
                break;
            case 2:
                currentRunway = attemptTakeOff(runways, currentRunway);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                displayWaitingForTakeOff(runways);
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                System.out.println("Please enter a valid command number");
            }
            System.out.print("\n");
        } while(!exit);

    }

    //prints the menu
    //this is called everytime the menu loop is repeated
    private static void printMenu() {
        System.out.println("\t1. Plane enters the system.\n" +
                           "\t2. Plane takes off.\n" +
                           "\t3. Plane is allowed to re-enter a runway\n" +
                           "\t4. Runway opens.\n" +
                           "\t5. Runway closes.\n" +
                           "\t6. Display info about planes waiting to take off.\n" +
                           "\t7. Display info about planes waiting to be allowed to re-enter a runway.\n" +
                           "\t8. Display number of planes who have taken off.\n" +
                           "\t9. Exit program.");
    }

    //this method is called when the application is starts running. This method gets the initial number of active runways and information of those runways.
    //this method has been tested and works
    private static void getInitialRunways(ListArrayBased<Runway> runways) throws IOException{
        System.out.print("Welcome to the Airport System!\nEnter number of runways: ");
        int amount = Integer.parseInt(reader.readLine());
        System.out.println(amount); //feedback for output file

        String name; //temp variable for storing user input
        int counter = 1; //counter for below loop
        //while loop used in case user enters a duplicate name by mistake
        while(counter <= amount){
                System.out.print("Enter name of runway " + counter + ": ");
                name = reader.readLine();
                System.out.println(name); //feedback for output file
                try {
                    validateRunwayName(name, runways); //throws AirportException if user specified name is not unique
                    runways.add(counter - 1, new Runway(name));
                    counter++;
                }
                catch (AirportException e) {
                        System.out.println(e.getMessage() + " Please choose another name.");
                }
        }
    }

    //validation method for runway names. Throws an AirportException if the string argument is already a name of a runway
    //this method has been tested and works
    private static void validateRunwayName(String name, ListArrayBased<Runway> runways) throws AirportException{
        for(int i = 0; i < runways.size(); i++) {
                if(runways.get(i).getName().equalsIgnoreCase(name))
                        throw new AirportException ("Runway " + name + " already exists.");
        }
    }

    //this method adds a user specified flight to a runway
    //this method has been tested and works
    private static void addFlight(ListArrayBased<Runway> runways) throws IOException{
        System.out.print("Enter flight number: ");
        String number = reader.readLine();
        System.out.println(number);

        System.out.print("Enter destination: ");
        String destination = reader.readLine();
        System.out.println(destination);

        System.out.print("Enter runway: ");
        String runway = reader.readLine();
        System.out.println(runway);

        //the below line searches for the runway specified, then creates a flight based off of given information enqueues it in that runways flight queue.
        findRunway(runways, runway).addFlight(new Flight(number, destination));
    }

    //this method attempts to allow a flight to take off
    //assumes that there is at least one plane queued in one runway (test for this before calling this)
    //incomplete (not tested)
    private static int attemptTakeOff(ListArrayBased<Runway> runways, int currentRunway) throws IOException {
        for(int i = 0; i < runways.size(); i++) {
                if(runways.get(currentRunway).hasFlights()) {
                        Runway runway = runways.get(currentRunway);
                        Flight flight = runway.getNextFlight();

                        System.out.print("Is flight " + flight + " cleared for takeoff(Y/N): ");
                        String response = reader.readLine();
                        System.out.println(response);

                        if(response.equalsIgnoreCase("Y")){
                                System.out.println("Flight " + flight.getFlightNumber() + " has now taken off from runway " + runway);
                        }
                        else  {//if the answer isn't yes, its no (assuming correct input
                                System.out.println("Flight " + flight.getFlightNumber() + " is now waiting to be allowed to re-enter a runway.");
                                runway.addWaitingToReEnterFlight(flight);
                        }
                }
                else
                        currentRunway = (currentRunway + 1) % runways.size();
        }
        return currentRunway;

    }

    //this method displays information about the flights that are waiting to take off
    //this method has been tested and works
    private static void displayWaitingForTakeOff(ListArrayBased<Runway> runways) {
        for(int i = 0; i < runways.size(); i++) {
            Runway current = runways.get(i);
            if(current.getFlights().isEmpty()) //display default message if runway has no queued flights
                System.out.println("No planes are waiting for takeoff on runway " + current.getName() + "!");
            else {
                System.out.println("These planes are waiting for takeoff on runway " + current + ":\n" +
                        current.toStringFlights());
            }
        }
    }

    //this method will search for a runway based on a given string
    //returns the runway if found and null if not found
    //made this a method in case we need it in multiple places
    //this method has been tested and works.
    private static Runway findRunway(ListArrayBased<Runway> runways, String name) {
        Runway runway = null;
        for(int i = 0; i < runways.size(); i++) {
                if(runways.get(i).getName().equalsIgnoreCase(name))
                        runway = runways.get(i);
        }
        return runway;
    }
}
