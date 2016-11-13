package xyz.jameskr.fm;

/**
 * Created by James on 11/12/16.
 */
public enum MainMenuOptions {
    SET_CLOCK(0, "Set Clock"),
    RESET_SCHEDULE(1, "Reset Schedule"),
    ADD_AIRLINE(2, "Add Airline"),
    ADD_FLIGHT(3, "Add Flight"),
    CANCEL_FLIGHT(4, "Cancel Flight"),
    SHOW_FLIGHT_INFO(5, "Show Flight Information"),
    SHOW_DEPART(6, "Show Departures"),
    SHOW_ARRIVAL(7, "Show Arrivals"),
    EXIT(8, "Close Application");

    private final int id;
    private final String desc;

    MainMenuOptions(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getID() {
        return id;
    }

    public String getDescription() {
        return desc;
    }
}