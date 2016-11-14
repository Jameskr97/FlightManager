package xyz.jameskr.fm;

import xyz.jameskr.fm.menu.ConsoleMenu;
import xyz.jameskr.fm.menu.MainMenuOptions;
import xyz.jameskr.fm.schedule.FlightSchedule;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JFlightManager. By James ");
        FlightSchedule schedule = new FlightSchedule();
        ConsoleMenu menu = new ConsoleMenu(MainMenuOptions.values());


        while (true) {
            menu.printOptions();
            int x = menu.getResponse();
            if (x == MainMenuOptions.SET_CLOCK.getID()) {
                schedule.setTime();

            } else if (x == MainMenuOptions.RESET_SCHEDULE.getID()) {
                schedule.clearSchedule();

            } else if (x == MainMenuOptions.MANAGE_AIRLINES.getID()) {
                schedule.manageAirlines();

            } else if (x == MainMenuOptions.ADD_FLIGHT.getID()) {
                schedule.addFlight();

            } else if (x == MainMenuOptions.CANCEL_FLIGHT.getID()) {
                schedule.cancelFlight();

            } else if (x == MainMenuOptions.SHOW_FLIGHT_INFO.getID()) {
                schedule.getFlightInformation();

            } else if (x == MainMenuOptions.SHOW_DEPART.getID()) {
                System.out.println("Option not implemented yet.");

            } else if (x == MainMenuOptions.SHOW_ARRIVAL.getID()) {
                System.out.println("Option not implemented yet.");

            } else if (x == MainMenuOptions.EXIT.getID()) {
                System.out.println("Saving...");
                System.out.println("Thanks for using JFlightManager");
                System.exit(0);
            }
        }


    }

}
