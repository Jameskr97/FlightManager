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

    /**
     * Word for related status character
     */
    private String description;

    /**
     * Status character
     */
    private char typeChar;

    FlightType(String desc, char type) {
        this.description = desc;
        this.typeChar = type;
    }

    public String getDesc() {
        return description;
    }

    public char getTypeChar() {
        return typeChar;
    }

    /**
     * Get description for given character
     *
     * @param type character data type
     * @return Description of character
     */
    public static String getDesc(char type) {
        for (FlightType t : FlightType.values()) {
            if (t.getTypeChar() == type) {
                return t.getDesc();
            }
        }
        return null; // Should never happen if this FlightType enum is always used.
    }
}
