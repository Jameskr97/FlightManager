package xyz.jameskr.fm;

import xyz.jameskr.fm.menu.ConsoleMenu;
import xyz.jameskr.fm.schedule.FlightSchedule;
import xyz.jameskr.fm.schedule.enums.FlightStatus;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JFlightManager. By James ");
        FlightSchedule schedule = new FlightSchedule();

        String[] options = {
                "Set Clock",
                "Reset Schedule",
                "Manage Airlines",
                "Add Flight",
                "Cancel Flight",
                "Show Flight Information",
                "Show Departures",
                "Show Arrivals",
                "Find flight between airport",
                "Close Application"
        };
        ConsoleMenu menu = new ConsoleMenu(options);

        while (true) {
            menu.printOptions();
            int x = menu.getResponse();
            if (x == 0) {
                schedule.setTime();

            } else if (x == 1) {
                schedule.clearSchedule();

            } else if (x == 2) {
                schedule.manageAirlines();

            } else if (x == 3) {
                schedule.addFlight();

            } else if (x == 4) {
                schedule.cancelFlight();

            } else if (x == 5) {
                schedule.getFlightInformation();

            } else if (x == 6) {
                schedule.showStatusInformation(FlightStatus.DEPARTED);

            } else if (x == 7) {
                schedule.showStatusInformation(FlightStatus.ARRIVED);

            } else if (x == 8) {
                schedule.findConnectingFlights();

            } else if (x == 9) {
                System.out.println("Saving...");
                System.out.println("Thanks for using JFlightManager");
                System.exit(0);
            }
        }


    }

}
