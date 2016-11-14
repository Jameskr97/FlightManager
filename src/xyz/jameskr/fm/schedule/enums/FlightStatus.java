package xyz.jameskr.fm.schedule.enums;

/**
 * @author James 
 * @date 11/14/16
 */
public enum FlightStatus {
    SCHEDULED('S'),
    DEPARTED('D'),
    ARRIVED('A'),
    CANCELED('C');

    private char statusChar;

    FlightStatus(char statusChar) {
        this.statusChar = statusChar;
    }

    public char getStatusChar() {
        return statusChar;
    }
}
