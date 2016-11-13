package xyz.jameskr.fm.schedule;

import java.util.ArrayList;

/**
 * @author James 
 * @date 11/12/16
 */
public class Airline {
    private String name;
    private String code;
    private Aircraft aircraft;

    public Airline(String name, String code){
        this.name = name;
        this.code = code;
        this.aircraft = Aircraft.DEFAULT_AIRCRAFT;
    }

    public String getName() {
        return name;
    }

    public String getAirlineCode() {
        return code;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }
}
