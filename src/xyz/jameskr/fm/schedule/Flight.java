package xyz.jameskr.fm.schedule;

import xyz.jameskr.fm.schedule.enums.FlightStatus;
import xyz.jameskr.fm.schedule.enums.FlightType;

/**
 * Stores information about a flight.
 *
 * @author James 
 * @date 11/12/16
 */
public class Flight {

    /**
     * Airline conducting the flight.
     */
    private Airline airline;

    /**
     * Departure and arrival information for the flight
     */
    private DepartureArrivalInfo departureInfo, arrivalInfo;

    /**
     * Flight number
     */
    private int flightNumber;

    /**
     * Flight status
     */
    private char flightStatus;

    /**
     * Flight type
     */
    private char flightType;

    /**
     * Constructor for the flight flightStatus.
     *
     * @param airline       Airline conducting flight
     * @param flightNum     Identification number for flight
     * @param flightType    Whether or not this is domestic of international
     * @param departureInfo Departure information for flight
     * @param arrivalInfo   Arrival information for flight
     */
    public Flight(Airline airline, int flightNum, char flightType, DepartureArrivalInfo departureInfo, DepartureArrivalInfo arrivalInfo) {
        this.airline = airline;
        this.flightNumber = flightNum;
        this.flightType = flightType;
        this.departureInfo = departureInfo;
        this.arrivalInfo = arrivalInfo;
        this.flightStatus = FlightStatus.SCHEDULED.getStatusChar();
    }

    /**
     * Prints all information about this flight to the console
     */
    public void printFlightInfo() {
        System.out.printf("Flight: %s\n", this.getFlightCode());
        System.out.printf("Type: %s\n", FlightType.getDesc(this.flightType));
        System.out.printf("Status: %s\n", FlightStatus.getDesc(this.flightStatus));
        System.out.printf("Economy Class Seats: %s\n", airline.getAircraft().getEconomyClassPassengers());
        System.out.printf("Business Class Seats: %s\n", airline.getAircraft().getBusinessClassPassengers());
        System.out.printf("First Class Seats: %s\n", airline.getAircraft().getFirstClassPassengers());
        System.out.printf("Departure day: %s\n", this.departureInfo.getDayOfWeek());
        System.out.printf("Departure time: %s\n", this.departureInfo.getTime());
        System.out.printf("Departure airport: %s\n", this.departureInfo.getAirportCode());
        System.out.printf("Departure gate: %s\n", this.departureInfo.getAirportGate());

        System.out.printf("Arrival day: %s\n", this.arrivalInfo.getDayOfWeek());
        System.out.printf("Arrival time: %s\n", this.arrivalInfo.getTime());
        System.out.printf("Arrival airport: %s\n", this.arrivalInfo.getAirportCode());
        System.out.printf("Arrival gate: %s\n", this.arrivalInfo.getAirportGate());
    }

    /**
     * Returns flight flightStatus
     *
     * @return Status of flight
     */
    public char getStatus() {
        return flightStatus;
    }

    /**
     * Set flight flightStatus
     *
     * @param status new flight flightStatus
     */
    public void setStatus(FlightStatus status) {
        this.flightStatus = status.getStatusChar();
    }

    /**
     * Gets the airline code and appends the flight number
     *
     * @return Flight code
     */
    public String getFlightCode() {
        return airline.getAirlineCode() + flightNumber;
    }

    /**
     * Depart info getter
     *
     * @return depart info
     */
    public DepartureArrivalInfo getDepartInfo() {
        return departureInfo;
    }

    /**
     * Arrive info getter
     *
     * @return arrive info
     */
    public DepartureArrivalInfo getArriveInfo() {
        return arrivalInfo;
    }

    /**
     * Airline getter
     *
     * @return airline
     */
    public Airline getAirline() {
        return airline;
    }
}
