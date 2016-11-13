package xyz.jameskr.fm;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author James 
 * @date 11/13/16
 */
public class FlightSchedule {
    private int currentTime;
    private ArrayList<Airline> airlines;
    private HashMap<String, Flight> flights;

    public FlightSchedule(){
        airlines = new ArrayList<>();
        flights = new HashMap<>();
    }

    public void clearSchedule(){

        System.out.print("Are you sure you want to delete all flights?\n↦ ");
        String response = Util.getInput();
        if (response.equals("y") || response.equals("yes")){
            flights.clear();
            System.out.println("Schedule cleared.");
        } else {
            System.out.println("Schedule unchanged.");
        }

    }



}
