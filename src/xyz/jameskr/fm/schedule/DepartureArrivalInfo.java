package xyz.jameskr.fm.schedule;

/**
 * Stores information about when the aircraft will arrive or depart
 *
 * @author James 
 * @date 11/12/16
 */
public class DepartureArrivalInfo {
    private String airportCode, airportGate;
    private char dayOfWeek;
    private int time;

    public DepartureArrivalInfo(String code, String gate, char dayOfWeek, int time) {
        this.airportCode = code;
        this.airportGate = gate;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportGate() {
        return airportGate;
    }

    public char getDayOfWeek() {
        return dayOfWeek;
    }

    public int getTime() {
        return time;
    }
}
