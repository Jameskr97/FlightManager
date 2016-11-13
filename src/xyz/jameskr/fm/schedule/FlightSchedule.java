package xyz.jameskr.fm.schedule;

import xyz.jameskr.fm.Util;
import xyz.jameskr.fm.menu.ConsoleMenu;
import xyz.jameskr.fm.menu.Interrogator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author James 
 * @date 11/13/16
 */
public class FlightSchedule {
    private int currentTime;
    private ArrayList<Airline> airlines;
    private HashMap<String, Flight> flights;

    public FlightSchedule() {
        airlines = new ArrayList<>();
        flights = new HashMap<>();
        airlines.add(0, new Airline("Delta Air", "DA"));
        airlines.add(1, new Airline("James Air", "JA"));
    }

    public void clearSchedule() {

        System.out.print("Are you sure you want to delete all flights?\n↦ ");
        String response = Util.getInput();
        if (response.equals("y") || response.equals("yes")) {
            flights.clear();
            System.out.println("Schedule cleared.");
        } else {
            System.out.println("Schedule unchanged.");
        }

    }


    // AIRLINE MANAGEMENT
    public void manageAirlines() {
        ConsoleMenu menu = new ConsoleMenu("Manage airline menu");
        menu.addOption(0, "List Airlines");
        menu.addOption(1, "Add Airline");
        menu.addOption(2, "Remove Airline");
        menu.addOption(3, "Return to main menu");

        while (true) {
            menu.printOptions();
            int res = menu.getResponse();
            if (res == 0) {
                this.listAirlines();
            } else if (res == 1) {
                this.addAirline();
            } else if (res == 2) {
                System.out.println("Option not implemented yet.");
            } else if (res == 3) {
                System.out.println("Returning...");
                break;
            }
        }


    }

    private void listAirlines() {
        if (airlines.size() == 0) {
            System.out.println("There are no airlines.");
        } else {
            System.out.println("Airline\t\t\tCode\tAircraft");
            System.out.println("=====================================");
            for (Airline a : airlines)
                System.out.printf("%s\t\t%s\t\t%s\n", a.getName(), a.getAirlineCode(), a.getAircraft().getName());
        }
    }

    private void addAirline() {
        // Get airline name and code...
        Interrogator asker = new Interrogator();
        asker.addQuestion(0, "Enter Airline Name: ", "Airline name already exists.", response -> {
            boolean canProceed = true; // As in "Can Proceed to next question."
            for (Airline a : airlines) {
                if (a.getName().equals(response)) {
                    canProceed = false;
                    break;
                }
            }
            return canProceed;
        });

        asker.addQuestion(1, "Enter Airline code: ", "Airline code already exists.", response -> {
            boolean canProceed = true; // As in "Can Proceed to next question."
            for (Airline a : airlines) {
                if (a.getAirlineCode().equals(response)) {
                    canProceed = false;
                    break;
                }
            }
            return canProceed;
        });

        String[] response = asker.ask();
        if (response == null) { // Will only return null if user responded with "no" to a try again prompt
            System.out.println("Operation canceled.");
            return;
        }

        // Create airline...
        Airline airline = new Airline(response[0], response[1]);
        this.airlines.add(airline);

        // Print aircraft data and wait for enter press.
        System.out.println(airline.getAircraft());
        System.out.printf("%s sucessfully added. Press enter to continue.", airline.getName());
        Util.safeWait();
    }

}
