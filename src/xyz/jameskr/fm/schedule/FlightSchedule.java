package xyz.jameskr.fm.schedule;

import xyz.jameskr.fm.Util;
import xyz.jameskr.fm.menu.ConsoleMenu;
import xyz.jameskr.fm.menu.Interrogator;
import xyz.jameskr.fm.schedule.enums.FlightType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class which stores information about flight schedule.
 *
 * @author James 
 * @date 11/13/16
 */
public class FlightSchedule {

    private int currentTime;
    private ArrayList<Airline> airlines;
    private HashMap<String, Flight> flights; // Flight code, flight

    public FlightSchedule() {
        airlines = new ArrayList<>();
        flights = new HashMap<>();
        airlines.add(0, new Airline("Delta Air", "DA"));
        airlines.add(1, new Airline("James Air", "JA"));

        //Adding sample flight data
        flights.put("DA1234", new Flight(this.getAirline("DA"), 1234, FlightType.DOMESTIC.getTypeChar(), new DepartureArrivalInfo("BOS", "A11", 'M', 1200), new DepartureArrivalInfo("BOS", "A11", 'M', 1300)));
        flights.put("JA4321", new Flight(this.getAirline("JA"), 4321, FlightType.DOMESTIC.getTypeChar(), new DepartureArrivalInfo("BOS", "A19", 'M', 1200), new DepartureArrivalInfo("BOS", "A19", 'M', 1300)));

    }


    /**
     * Clear all flights from the dictionary.
     */
    public void clearSchedule() {
        System.out.print("Are you sure you want to delete all flights? (y/n) \n↦ ");
        String response = Util.getInput();
        if (response.equals("y") || response.equals("yes")) {
            flights.clear();
            System.out.println("Schedule cleared.");
        } else {
            System.out.println("Schedule unchanged.");
        }

    }

    /**
     * Presents menu to manage airlines
     */
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

    /**
     * List all the airlines currently in database
     */
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

    /**
     * Add an airline using the Interrogator class.
     */
    private void addAirline() {
        // Get airline name and code...
        Interrogator asker = new Interrogator();
        asker.addQuestion(0, "Enter Airline Name: ", "Airline name already exists.", (response, pastResponses) -> this.doesAirlineExist(response));

        asker.addQuestion(1, "Enter Airline code: ", "Airline code already exists.", (response, pastResponses) -> {
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
        if (response == null) return;

        // Create airline...
        Airline airline = new Airline(response[0], response[1]);
        this.airlines.add(airline);

        // Print aircraft data and wait for enter press.
        System.out.println(airline.getAircraft());
        System.out.printf("%s sucessfully added. Press enter to continue.", airline.getName());
        Util.safeWait();
    }


    /**
     * Add a flight using the Interrogator class.
     */
    public void addFlight() {
        Interrogator interro = new Interrogator();
        interro.addQuestion(0, "Enter Airline code: ", "Airline code does not exist.", (response, pastResponses) -> {
            boolean exists = false;
            for (Airline a : airlines) {
                if (a.getAirlineCode().equals(response)) {
                    exists = true;
                    break;
                }
            }
            return exists;
        });

        interro.addQuestion(1, "Enter flight number: ", "Flight number already exists.", (response, pastResponses) -> {
            String airlineCode = pastResponses[0];
            String flightCode = airlineCode + response;
            if (flights.containsKey(flightCode))
                return false;
            return true;
        });

        interro.addQuestion(2, "Enter flight type (D -> Domestic, I -> International): ", (response, pastResponses) -> {
            boolean validResponse = false;
            if (response.equalsIgnoreCase("d") || response.equalsIgnoreCase("i"))
                validResponse = true;
            return validResponse;
        });

        interro.addQuestion(3, "Enter departure airport code: ", "Airport code does not exist.", (response, pastResponses) -> true);
        interro.addQuestion(4, "Enter departure gate: ", "Incorrect format.", (response, pastResponses) -> true); // Is it possible for this to have an incorrect format?
        interro.addQuestion(5, "Enter departure day (U, M, T, W, R, F, S): ", "Invalid day.", (response, pastResponses) -> this.isDayOfWeekChar(response));
        interro.addQuestion(6, "Enter departure time (Ex: 1130, 1540, 0930): ", "Invalid time.", (response, pastResponses) -> this.isValidTime(response));
        interro.addQuestion(7, "Enter arrival airport code: ", "Airport code does not exist.", (response, pastResponses) -> true);
        interro.addQuestion(8, "Enter arrival gate: ", "Incorrect format.", (response, pastResponses) -> true); // Is it possible for this to have an incorrect format?
        interro.addQuestion(9, "Enter arrival day (U, M, T, W, R, F, S): ", "Invalid day.", (response, pastResponses) -> this.isDayOfWeekChar(response));
        interro.addQuestion(10, "Enter arrival time (Ex: 1130, 1540, 0930): ", "Invalid time.", (response, pastResponses) -> this.isValidTime(response));

        String[] res = interro.ask();
        Airline airline = this.getAirline(res[0]);
        int flightNum = Integer.parseInt(res[1]);
        char flightType = res[2].charAt(0);
        DepartureArrivalInfo departInfo = new DepartureArrivalInfo(res[3], res[4], res[5].charAt(0), Integer.valueOf(res[6]));
        DepartureArrivalInfo arrivalInfo = new DepartureArrivalInfo(res[7], res[8], res[9].charAt(0), Integer.valueOf(res[10]));

        Flight flight = new Flight(airline, flightNum, flightType, departInfo, arrivalInfo);
        flights.put(flight.getFlightCode(), flight);

        System.out.printf("Flight %s scheduled successfully.\n", flight.getFlightCode());
    }

    public void getFlightInformation() {
        Interrogator ask = new Interrogator();
        ask.addQuestion(0, "Enter airline Code: ", "Airline code does not exist.", (response, pastResponses) -> this.doesAirlineExist(response));
        ask.addQuestion(1, "Enter flight number: ", "Flight number does not exist.", (response, pastResponses) -> {
            String airlineCode = pastResponses[0];
            return flights.containsKey(airlineCode + response);
        });

        String flightID = String.join("", ask.ask());
        flights.get(flightID).printFlightInfo();
    }

    private boolean doesAirlineExist(String iataCode) {
        boolean canProceed = true;
        for (Airline a : airlines) {
            if (a.getName().equals(iataCode)) {
                canProceed = false;
                break;
            }
        }
        return canProceed;
    }

    /**
     * Verifies the entered day of week.
     *
     * @param dayOfWeek Single char for day of week
     * @return true if dayOfWeek is valid char
     */
    private boolean isDayOfWeekChar(String dayOfWeek) {
        if (dayOfWeek.length() != 1) return false;
        char day = dayOfWeek.toUpperCase().charAt(0);
        return (day == 'U' || day == 'M' || day == 'T' || day == 'W' || day == 'R' || day == 'F' || day == 'S');
    }

    /**
     * Verifies the entered time.
     *
     * @param timeString
     * @return true if timeString is valid time
     */
    private boolean isValidTime(String timeString) {
        if (timeString.length() != 4) return false;
        int hour, minute;
        try {
            minute = Integer.valueOf(timeString.substring(0, 2));
            hour = Integer.valueOf(timeString.substring(2));
        } catch (NumberFormatException e) {
            return false;
        }
        if (0 > hour || hour > 12) return false;
        if (0 > minute || minute > 60) return false;
        return false;
    }

    /**
     * Looks through airlines find the one with the given IATA code.
     *
     * @param iataCode IATA code of airline
     * @return Airline with corresponding IATA code.
     */
    private Airline getAirline(String iataCode) {
        for (Airline a : airlines) {
            if (a.getAirlineCode().equalsIgnoreCase(iataCode))
                return a;
        }
        return null; // Because of verification this should never happen.
    }

}
