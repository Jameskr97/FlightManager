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

    char dayChar;

    Day(char dayChar) {
        this.dayChar = dayChar;
    }

    public char getDayChar() {
        return dayChar;
    }

    public static Day getDay(char dayChar) {
        for (Day d : Day.values())
            if (d.getDayChar() == dayChar)
                return d;
        return null; // This should never happen because dayChar parameter will always be from source or validated.
    }
}
