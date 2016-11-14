package xyz.jameskr.fm.schedule.enums;

/**
 * Enum to easy interface with flight type required datatype of char.
 *
 * @author James 
 * @date 11/14/16
 */
public enum FlightType {
    DOMESTIC("Domestic", 'D'),
    INTERNATIONAL("International", 'I');

    private String description;
    private char type;

    FlightType(String desc, char type) {
        this.description = desc;
        this.type = type;
    }

    public String getDesc() {
        return description;
    }

    public char getType() {
        return type;
    }
}
