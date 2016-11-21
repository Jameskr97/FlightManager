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
     * Maximum number of economy class passengers
     */
    private int economyClassPassengers;

    /**
     * Maximum number of business class passengers
     */
    private int businessClassPassengers;

    /**
     * Maximum number of first class passengers
     */
    private int firstClassPassengers;

    /**
     * Class Constructor
     */
    public Aircraft() {
        this.model = "Boeing 777-9";
        this.economyClassPassengers = 250;
        this.businessClassPassengers = 30;
        this.firstClassPassengers = 20;
    }

    /**
     * @return Aircraft name
     */
    public String getName() {
        return model;
    }

    /**
     * @return Num. Economy class passengers
     */
    public int getEconomyClassPassengers() {
        return economyClassPassengers;
    }

    /**
     * @return Num. Business class passengers
     */
    public int getBusinessClassPassengers() {
        return businessClassPassengers;
    }

    /**
     * @return Num. First class passengers
     */
    public int getFirstClassPassengers() {
        return firstClassPassengers;
    }
}
