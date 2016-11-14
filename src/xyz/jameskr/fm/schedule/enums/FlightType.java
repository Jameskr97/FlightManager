package xyz.jameskr.fm.schedule.enums;

/**
 * @author James 
 * @date 11/14/16
 */
public enum FlightType {
    DOMESTIC('D'),
    INTERNATIONAL('I');

    private char type;

    FlightType(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }
}
