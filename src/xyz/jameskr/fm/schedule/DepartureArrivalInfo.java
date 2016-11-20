package xyz.jameskr.fm.schedule;

/**
 * Stores information about when the aircraft will arrive or depart
 *
 * @author James 
 * @date 11/12/16
 */
public class DepartureArrivalInfo {

    /**
     * Strings for the airport code and airport gate
     */
    private String airportCode, airportGate;

    /**
     * Day of week this DepartureArrival event occurs
     */
    private char dayOfWeek;

    /**
     * Time of this DepartureArrival event
     */
    private int time;

    public DepartureArrivalInfo(String code, String gate, char dayOfWeek, int time) {
        this.airportCode = code;
        this.airportGate = gate;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
    }

    /**
     * @return Airport code of DepartureArrival event
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * @return Airport gate of DepartureArrival event
     */
    public String getAirportGate() {
        return airportGate;
    }

    /**
     * @return Day of week this DepartureArrival event occurs
     */
    public char getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * @return Time of this DepartureArrival event
     */
    public int getTime() {
        return time;
    }
}
