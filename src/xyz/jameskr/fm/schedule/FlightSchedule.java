package xyz.jameskr.fm.schedule;

import xyz.jameskr.fm.Util;
import xyz.jameskr.fm.menu.ConsoleMenu;
import xyz.jameskr.fm.menu.Interrogator;
import xyz.jameskr.fm.schedule.enums.Day;
import xyz.jameskr.fm.schedule.enums.FlightStatus;
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

    /**
     * Current time
     */
    private int currentTime;

    /**
     * Current Day of week
     */
    private char dayOfWeek;

    /**
     * ArrayList to track all current airlines
     */
    private ArrayList<Airline> airlines;

    /**
     * Map to store every flight
     */
    private HashMap<String, Flight> flights; // Flight code, flight

    /**
     * Class Constructor. Initializes above variables and adds sample data for faster testing.
     */
    public FlightSchedule() {
        airlines = new ArrayList<>();
        flights = new HashMap<>();
        airlines.add(0, new Airline("Delta Air", "DA"));
        airlines.add(1, new Airline("James Air", "JA"));

        //Adding sample flight data
        flights.put("DA1234", new Flight(this.getAirline("DA"), 1234, FlightType.DOMESTIC.getTypeChar(), new DepartureArrivalInfo("BOS", "A11", 'M', 1200), new DepartureArrivalInfo("BOS", "A19", 'M', 1300)));
        flights.put("DA4321", new Flight(this.getAirline("DA"), 4321, FlightType.DOMESTIC.getTypeChar(), new DepartureArrivalInfo("BOS", "A13", 'M', 1330), new DepartureArrivalInfo("BOS", "A17", 'M', 1400)));
        flights.put("DA2468", new Flight(this.getAirline("DA"), 2468, FlightType.DOMESTIC.getTypeChar(), new DepartureArrivalInfo("BOS", "A15", 'M', 1430), new DepartureArrivalInfo("BOS", "A15", 'M', 1500)));
        flights.put("DA1357", new Flight(this.getAirline("DA"), 1357, FlightType.DOMESTIC.getTypeChar(), new DepartureArrivalInfo("BOS", "A17", 'M', 1530), new DepartureArrivalInfo("BOS", "A14", 'M', 1600)));
        flights.put("DA3579", new Flight(this.getAirline("DA"), 3579, FlightType.DOMESTIC.getTypeChar(), new DepartureArrivalInfo("BOS", "A19", 'M', 1630), new DepartureArrivalInfo("BOS", "A11", 'M', 1700)));
        setTime('M', 1435);

    }

    /**
     * Interactively the current time.
     */
    public void setTime() {
        Interrogator asker = new Interrogator();
        asker.addQuestion(0, "Enter new day of week: ", "That is not a valid day of the week.", (response, pastResponses) -> this.isDayOfWeekChar(response));
        asker.addQuestion(1, "Enter new 24 hour time: ", "That is not a valid 24 hour time.", (response, pastResponses) -> this.isValidTime(response));
        String[] response = asker.ask();
        if (response == null) {
            System.out.println("Operation canceled.");
            return;
        }
        this.dayOfWeek = response[0].charAt(0);
        this.currentTime = Integer.valueOf(response[1]);
        this.setTime(this.dayOfWeek, this.currentTime);
        System.out.printf("Day set to %c and time set to %d.\nPress Enter to continue.", this.dayOfWeek, this.currentTime);
        Util.safeWait();

    }

    /**
     * Sets the time to the given parameters, and modifies the states of the flights in array based off of the given time.
     *
     * @param dayOfWeek New day of the week
     * @param timeNow   New time
     */
    private void setTime(char dayOfWeek, int timeNow) {
        this.dayOfWeek = dayOfWeek;
        this.currentTime = timeNow;
        // Modify status of flights according to current time.
        for (String flightID : flights.keySet()) {
            Flight f = flights.get(flightID);
            // Don't touch the canceled flights.
            if (f.getStatus() == FlightStatus.CANCELED.getStatusChar())
                continue;


            DepartureArrivalInfo arrive = f.getArriveInfo(), depart = f.getDepartInfo();

            // If arrival day is before today
            if (Day.getDay(arrive.getDayOfWeek()).ordinal() < Day.getDay(dayOfWeek).ordinal()) {
                f.setStatus(FlightStatus.ARRIVED);// Then they already arrived
                continue;
            } else if (Day.getDay(arrive.getDayOfWeek()).ordinal() > Day.getDay(dayOfWeek).ordinal()) { // If arrival day is after today
                if (depart.getTime() >= timeNow) { // And depart time is after now
                    f.setStatus(FlightStatus.SCHEDULED); // It's scheduled.
                    continue;
                } else if (depart.getTime() < timeNow) { // Else if depart time is before now
                    f.setStatus(FlightStatus.DEPARTED); // It's departed
                    continue;
                }
            }

            // If arrival day is today
            if (Day.getDay(arrive.getDayOfWeek()).ordinal() == Day.getDay(dayOfWeek).ordinal()) {
                if (arrive.getTime() <= timeNow) { // And the arrive time is before now
                    f.setStatus(FlightStatus.ARRIVED); // They arrived
                    continue;
                } else if (arrive.getTime() > timeNow && timeNow > depart.getTime()) { // If the arrive time is after now, but depart time is before now
                    f.setStatus(FlightStatus.DEPARTED); // We're departed
                    continue;
                } else if (timeNow < depart.getTime()) { // Else if depart time is after now...
                    f.setStatus(FlightStatus.SCHEDULED);  // We're scheduled
                }
            }


        }
    }

    /**
     * Clear all flights from the dictionary.
     */
    public void clearSchedule() {
        Interrogator ask = new Interrogator();
        ask.addQuestion(0, "Are you sure you want to delete all flights? (y/n): ", "Invalid boolean response.",
                (response, pastResponses) -> this.booleanResponseValidator(response));
        String[] response = ask.ask();
        if (response[0].equalsIgnoreCase("y")) {
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
                this.removeAirline();
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
        asker.addQuestion(0, "Enter Airline Name: ", "Airline name is invalid/already exists.", (response, pastResponses) -> !this.airlineNameExistsValidator(response));
        asker.addQuestion(1, "Enter Airline code: ", "Airline code is invalid/already exists.", (response, pastResponses) -> !this.airlineCodeExistsValidator(response));

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
     * Remove an airline and all related flights.
     */
    private void removeAirline() {
        Interrogator ask = new Interrogator();
        ask.addQuestion(0, "Enter airline code: ", "Airline does not exist.", (response, pastResponses) -> this.airlineCodeExistsValidator(response));
        String[] res1 = ask.ask();
        if (res1 == null) {
            System.out.println("Operation canceled.");
            return;
        }
        Airline a = this.getAirline(res1[0]);

        Interrogator last = new Interrogator();
        String question = String.format("Are you sure you want to remove airline %s and all associated flights? (y/n): ", a.getName());
        last.addQuestion(0, question, "Invliad boolean response", (response, pastResponses) -> this.booleanResponseValidator(response));
        String ans = last.ask()[0];

        if (ans.equalsIgnoreCase("y")) {
            ArrayList<String> flightsToRemove = new ArrayList<>();
            for (String s : flights.keySet()) {
                Flight f = flights.get(s);
                if (f.getAirline().getAirlineCode().equalsIgnoreCase(a.getAirlineCode()))
                    flightsToRemove.add(s);
            }
            flightsToRemove.forEach(flights::remove);
            airlines.remove(a);
            System.out.println("Airline deleted.");
        } else {
            System.out.println("Airline unchanged.");
        }
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

        interro.addQuestion(1, "Enter flight number: ", "Flight number is invalid/already exists.",
                (response, pastResponses) -> !this.doesFlightIDExist(pastResponses[0] + response));


        interro.addQuestion(2, "Enter flight type (D -> Domestic, I -> International): ", (response, pastResponses) -> {
            boolean validResponse = false;
            if (response.equalsIgnoreCase("d") || response.equalsIgnoreCase("i"))
                validResponse = true;
            return validResponse;
        });

        interro.addQuestion(3, "Enter departure airport code: ", "Airport code does not exist.", (response, pastResponses) -> this.verifyAirportCode(response));
        interro.addQuestion(4, "Enter departure gate: ", "Incorrect format.", (response, pastResponses) -> this.verifyAirportCode(response)); // Is it possible for this to have an incorrect format?
        interro.addQuestion(5, "Enter departure day (U, M, T, W, R, F, S): ", "Invalid day.", (response, pastResponses) -> this.isDayOfWeekChar(response));
        interro.addQuestion(6, "Enter departure time (Ex: 1130, 1540, 0930): ", "Invalid time.", (response, pastResponses) -> this.isValidTime(response));
        interro.addQuestion(7, "Enter arrival airport code: ", "Airport code does not exist.", (response, pastResponses) -> this.verifyAirportCode(response));
        interro.addQuestion(8, "Enter arrival gate: ", "Incorrect format.", (response, pastResponses) -> this.verifyAirportGate(response)); // Is it possible for this to have an incorrect format?
        interro.addQuestion(9, "Enter arrival day (U, M, T, W, R, F, S): ", "Invalid day.", (response, pastResponses) -> this.isDayOfWeekChar(response));
        interro.addQuestion(10, "Enter arrival time (Ex: 1130, 1540, 0930): ", "Invalid time.", (response, pastResponses) -> this.isValidTime(response));

        String[] res = interro.ask();
        if (res == null) {
            System.out.println("Operation Canceled.");
            return;
        }
        Airline airline = this.getAirline(res[0]);
        int flightNum = Integer.parseInt(res[1]);
        char flightType = res[2].charAt(0);
        DepartureArrivalInfo departInfo = new DepartureArrivalInfo(res[3], res[4], res[5].charAt(0), Integer.valueOf(res[6]));
        DepartureArrivalInfo arrivalInfo = new DepartureArrivalInfo(res[7], res[8], res[9].charAt(0), Integer.valueOf(res[10]));

        Flight flight = new Flight(airline, flightNum, flightType, departInfo, arrivalInfo);
        flights.put(flight.getFlightCode(), flight);

        System.out.printf("Flight %s scheduled successfully.\n", flight.getFlightCode());
    }

    /**
     * Cancels flights that are in the database
     */
    public void cancelFlight() {
        Interrogator ask = new Interrogator();
        ask.addQuestion(0, "Enter airline code: ", "Airline code does not exist.", (response, pastResponses) -> this.airlineCodeExistsValidator(response));
        ask.addQuestion(1, "Enter flight number: ", "Flight number does not exist.", (response, pastResponses) -> flights.containsKey(pastResponses[0] + response));
        String[] res = ask.ask();
        String flightNum = String.join("", res);
        Flight f = flights.get(flightNum);

        Interrogator confirm = new Interrogator();
        confirm.addQuestion(0, String.format("Are you sure you want to cancel flight %s going from %s to %s? (y/n): ", flightNum, f.getDepartInfo().getAirportCode(), f.getArriveInfo().getAirportCode()), (response, pastResponses) -> this.booleanResponseValidator(response));
        String[] ans = confirm.ask();

        if (ans[0].equalsIgnoreCase("y")) {
            flights.get(flightNum).setStatus(FlightStatus.CANCELED);
            System.out.printf("Flight %s canceled.\n", flightNum);
        } else {
            System.out.printf("Flight %s unchanged.\n", flightNum);
        }
    }

    /**
     * Prints flight information based on given airline code and flight number
     */
    public void getFlightInformation() {
        Interrogator ask = new Interrogator();
        ask.addQuestion(0, "Enter airline Code: ", "Airline code does not exist.", (response, pastResponses) -> this.airlineCodeExistsValidator(response));
        ask.addQuestion(1, "Enter flight number: ", "Flight number does not exist.", (response, pastResponses) -> flights.containsKey(pastResponses[0] + response));

        String[] response = ask.ask();
        if (response == null) {
            System.out.println("Operation canceled.");
            return;
        }
        String flightID = String.join("", response);
        flights.get(flightID).printFlightInfo();
    }

    /**
     * Displays flight status info based on status parameter
     *
     * @param status Status to show, (must be arrived or departed)
     */
    public void showStatusInformation(FlightStatus status) {
        if (status != FlightStatus.DEPARTED && status != FlightStatus.ARRIVED) return;
        Interrogator ask = new Interrogator();
        ask.addQuestion(0, "Enter airport code: ", "Invalid code.", (response, pastResponses) -> response.length() == 3);
        ask.addQuestion(1, "Enter day: ", "Invalid day.", (response, pastResponses) -> this.isDayOfWeekChar(response));
        String[] res = ask.ask();
        String airportCode = res[0];
        char dayOfWeek = res[1].charAt(0);

        // Get all flights that depart/arrive from given gate.
        ArrayList<Flight> dFlights = new ArrayList<>();
        for (String flightID : flights.keySet()) {
            Flight f = flights.get(flightID);

            // The following line gets departInfo if parameter is status DEPARTED and arriveInfo if parameter is status Arrived.
            DepartureArrivalInfo statusInfo = (status == FlightStatus.DEPARTED ? f.getDepartInfo() : f.getArriveInfo());
            if (statusInfo.getAirportCode().equalsIgnoreCase(airportCode)
                    && statusInfo.getDayOfWeek() == dayOfWeek
                    && f.getStatus() != (status == FlightStatus.DEPARTED ? FlightStatus.DEPARTED.getStatusChar() : FlightStatus.ARRIVED.getStatusChar()))
                dFlights.add(f);
        }

        if (dFlights.size() == 0) {
            System.out.printf("There are no flights %s %s.\n", (status == FlightStatus.DEPARTED ? "departing from" : "arriving to"), airportCode);
            return;
        }

        // Sort arrivals based on time (Bubble sort)
        dFlights = sortFlights(dFlights, true);


        System.out.printf("Current time is %d. %s %s:\n", this.currentTime, airportCode, (status == FlightStatus.DEPARTED ? "departures" : "arrivals"));
        System.out.printf("====================================================\n");
        System.out.printf("Flight Code\t\tStatus\tTime\tDestination\tGate\n");
        System.out.printf("====================================================\n");
        for (Flight f : dFlights) {
            DepartureArrivalInfo info = (status == FlightStatus.DEPARTED ? f.getDepartInfo() : f.getArriveInfo());
            System.out.printf("%s\t\t\t%s\t\t%d\t\t\t%s\t%s\n", f.getFlightCode(), f.getStatus(), info.getTime(), info.getAirportCode(), info.getAirportGate());
        }
        System.out.printf("====================================================\n");
        System.out.print("Press Enter to continue...");
        Util.safeWait();
    }

    /**
     * Validator which verifies response is either "y" or "n"
     *
     * @param response User input
     * @return true if response is valid
     */
    private boolean booleanResponseValidator(String response) {
        return (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n"));
    }

    /**
     * Validator to check if a given airline exists
     *
     * @param iataCode airline iata code
     * @return true if response is valid
     */
    private boolean airlineCodeExistsValidator(String iataCode) {
        iataCode = iataCode.trim();
        if (iataCode.equals("") || iataCode.length() == 0)
            return true; // Assume these exists so they will never actually be created.
        boolean exists = false;
        for (Airline a : airlines) {
            if (a.getAirlineCode().equalsIgnoreCase(iataCode)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private boolean airlineNameExistsValidator(String airlineName) {
        airlineName = airlineName.trim();
        if (airlineName.equals("") || airlineName.length() == 0)
            return true; // Assume these exists so they will never actually be created.
        boolean exists = false;
        for (Airline a : airlines) {
            if (a.getName().equalsIgnoreCase(airlineName)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private boolean verifyAirportCode(String code) {
        return code.length() == 3;
    }

    private boolean verifyAirportGate(String code) {
        return code.length() == 3 || code.length() == 4;
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
            minute = Integer.valueOf(timeString.substring(2));
            hour = Integer.valueOf(timeString.substring(0, 2));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        if (0 > hour || hour > 24) return false;
        if (0 > minute || minute > 60) return false;
        return true;
    }

    private boolean doesFlightIDExist(String flightId) {
        if (flightId.length() != 6) return false;
        return flights.containsKey(flightId);
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

    /**
     * Sort an ArrayList of Flight objects based on time.
     *
     * @param flight    ArrayList of flight objects
     * @param ascending If it should be ascending or decending.
     * @return
     */
    private ArrayList<Flight> sortFlights(ArrayList<Flight> flight, boolean ascending) {
        for (int i = flight.size() - 1; i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                boolean flag;
                if (ascending)
                    flag = (flight.get(j - 1).getDepartInfo().getTime() > flight.get(j).getDepartInfo().getTime());
                else
                    flag = (flight.get(j - 1).getDepartInfo().getTime() < flight.get(j).getDepartInfo().getTime());

                if (flag) {
                    Flight t = flight.get(j - 1);
                    flight.set(j - 1, flight.get(j));
                    flight.set(j, t);
                }

            }
        }
        return flight;
    }
    
}
