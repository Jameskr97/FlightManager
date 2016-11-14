package xyz.jameskr.fm.schedule.enums;

/**
 * Enum to easy interface with flight status required datatype of char.
 *
 * @author James 
 * @date 11/14/16
 */
public enum FlightStatus {
    SCHEDULED("Scheduled", 'S'),
    DEPARTED("Departed", 'D'),
    ARRIVED("Arrived", 'A'),
    CANCELED("Canceled", 'C');

    private String description;
    private char statusChar;

    FlightStatus(String desc, char statusChar) {
        this.description = desc;
        this.statusChar = statusChar;
    }

    public String getDesc() {
        return description;
    }

    public char getStatusChar() {
        return statusChar;
    }

    public static String getDesc(char statusChar) {
        for (FlightStatus s : FlightStatus.values()) {
            if (s.getStatusChar() == statusChar) {
                return s.getDesc();
            }
        }
        return null; // Should never happen if this FlightStatus enum is always used.
    }

}
