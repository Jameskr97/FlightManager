package xyz.jameskr.fm.schedule;

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
    private int flightNumber;
    private char status;
    private char flightType;

    /**
     * Constructor for the flight status.
     *
     * @param airline Airline conducting flight
     * @param flightNum Identification number for flight
     * @param flightType Whether or not this is domestic of international
     * @param departureInfo Departure information for flight
     * @param arrivalInfo Arrival information for flight
     */
    public Flight(Airline airline, int flightNum, char flightType, DepartureArrivalInfo departureInfo, DepartureArrivalInfo arrivalInfo) {
        this.airline = airline;
        this.flightNumber = flightNum;
        this.flightType = flightType;
        this.departureInfo = departureInfo;
        this.arrivalInfo = arrivalInfo;
    }

    /**
     * Returns flight status
     *
     * @return Status of flight
     */
    public char getStatus() {
        return status;
    }

    /**
     * Set flight status
     *
     * @param status new flight status
     */
    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * Gets the airline code and appends the flight number
     *
     * @return Flight code
     */
    public String getFlightCode() {
        return airline.getAirlineCode() + flightNumber;
    }
}
