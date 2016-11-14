package xyz.jameskr.fm.schedule;

/**
 * Stores information about a flight.
 *
 * @author James 
 * @date 11/12/16
 */
public class Flight {
    private Airline airline;
    private DepartureArrivalInfo departureInfo, arrivalInfo;
    private int flightNumber;
    private char status;
    private char flightType;

    public Flight(Airline airline, int flightNum, char flightType, DepartureArrivalInfo departureInfo, DepartureArrivalInfo arrivalInfo) {
        this.airline = airline;
        this.flightNumber = flightNum;
        this.flightType = flightType;
        this.departureInfo = departureInfo;
        this.arrivalInfo = arrivalInfo;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getFlightCode() {
        return airline.getAirlineCode() + flightNumber;
    }
}
