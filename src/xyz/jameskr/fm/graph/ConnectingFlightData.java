package xyz.jameskr.fm.graph;

import xyz.jameskr.fm.schedule.Flight;

import java.util.ArrayList;

/**
 * Class to store flight paths from one airport to another when there is more than one flight.
 *
 * @author James
 * @date 11/20/16
 */
public class ConnectingFlightData {

    /**
     * List of flights in order to take them.
     */
    private ArrayList<Flight> flights;

    public ConnectingFlightData() {
        flights = new ArrayList<>();
    }

    /**
     * Add flight to flight list
     *
     * @param f
     */
    public void addFlight(Flight f) {
        flights.add(f);
    }

    /**
     * Calculates layover time for all the flights in the list
     *
     * @return layover time in minutes
     */
    public int getLayoverTime() {
        int totalTime = 0;
        for (int i = 0; i < flights.size() - 1; i++) {
            Flight one = flights.get(i);
            Flight two = flights.get(i + 1);
            totalTime += two.getDepartInfo().getTime() - one.getArriveInfo().getTime();
        }
        return totalTime;
    }

    /**
     * Return a string list of stops that will be made between first and last flight in list.
     *
     * @return String list of flights in order.
     */
    public String getFlightOrderString() {
        String res = "";
        for (int i = 0; i < flights.size(); i++) {
            res += flights.get(i).getDepartInfo().getAirportCode() + " -> ";
            if (i == flights.size() - 1)
                res += flights.get(i).getArriveInfo().getAirportCode();
        }
        return res;
    }

    /**
     * @return Total number of flights
     */
    public int getNumberFlights() {
        return flights.size();
    }

    /**
     * @return Flights array
     */
    public ArrayList<Flight> getFlights() {
        return flights;
    }
}
