package xyz.jameskr.fm.schedule.enums;

/**
 * Day enum to easily get day as char.
 *
 * @author James 
 * @date 11/14/16
 */
public enum Day {
    Sunday('U'),
    Monday('M'),
    Tuesday('T'),
    Wednesday('W'),
    Thursday('R'),
    Friday('F'),
    Saturday('S');

    /**
     * Char data type which the day of the week must be stored as (as the assignment requires).
     */
    char dayChar;

    Day(char dayChar) {
        this.dayChar = dayChar;
    }

    /**
     * @return returns character version of character
     */
    public char getDayChar() {
        return dayChar;
    }

    /**
     * Get enum object of character
     *
     * @param dayChar Character of enum object to get
     * @return Day enum object
     */
    public static Day getDay(char dayChar) {
        for (Day d : Day.values())
            if (d.getDayChar() == dayChar)
                return d;
        return null; // This should never happen because dayChar parameter will always be from source or validated.
    }
}
