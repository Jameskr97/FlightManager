package xyz.jameskr.fm.graph;

import xyz.jameskr.fm.schedule.Flight;

import java.util.ArrayList;

/**
 * @author James 
 * @date 11/20/16
 */
public class ConnectingFlightData {

    private ArrayList<Flight> flights;

    public ConnectingFlightData() {
        flights = new ArrayList<>();
    }

    public void addFlight(Flight f) {
        flights.add(f);
    }

    public int getLayoverTime() {
        int totalTime = 0;
        for (int i = 0; i < flights.size() - 1; i++) {
            Flight one = flights.get(i);
            Flight two = flights.get(i + 1);
            totalTime += two.getDepartInfo().getTime() - one.getArriveInfo().getTime();
        }
        return totalTime;
    }

    public String getFlightOrderString() {
        String res = "";
        for (int i = 0; i < flights.size(); i++) {
            res += flights.get(i).getDepartInfo().getAirportCode() + " -> ";
            if (i == flights.size() - 1)
                res += flights.get(i).getArriveInfo().getAirportCode();
        }
        return res;
    }

    public int getNumberFlights(){
        return flights.size();
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
}
