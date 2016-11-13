package xyz.jameskr.fm.schedule;

/**
 * Class which stores information about aircrafts.
 *
 * @author James 
 * @date 11/12/16
 */
public class Aircraft {

    /**
     * Static/Generic/Default aircraft to be used by all airlines
     */
    public static Aircraft DEFAULT_AIRCRAFT = new Aircraft();

    /**
     * Name of the aircraft
     */
    private String model;

    /**
     * Maximum square feet of cargo
     */
    private int cargoCapacity;

    /**
     * Maximum number of passengers
     */
    private int passengerCapacity;

    public Aircraft() {
        this.model = "Boeing 777-9";
        this.cargoCapacity = 5330;
        this.passengerCapacity = 400;
    }

    /**
     * Gets the aircraft name
     *
     * @return aircraft name
     */
    public String getName() {
        return model;
    }

    @Override
    public String toString() {
        return String.format("Aircraft: %s\nCargo Capacity: %d\nMax Passengers: %d", model, cargoCapacity, passengerCapacity);
    }
}
