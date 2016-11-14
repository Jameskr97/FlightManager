package xyz.jameskr.fm.schedule;

/**
 * Basic class which holds important airline information
 *
 * @author James 
 * @date 11/12/16
 */
public class Airline {
    private String name;
    private String code;
    private Aircraft aircraft;

    /**
     * Airline constructor. Name and code defined here so they remain constant.
     *
     * @param name Airline name
     * @param code Airline code
     */
    public Airline(String name, String code) {
        this.name = name;
        this.code = code;
        this.aircraft = Aircraft.DEFAULT_AIRCRAFT;
    }

    /**
     * Gets aircraft name
     *
     * @return aircraft name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets airline code
     *
     * @return airline code
     */
    public String getAirlineCode() {
        return code;
    }

    /**
     * Gets aircraft object
     *
     * @return aircraft object
     */
    public Aircraft getAircraft() {
        return aircraft;
    }
}
