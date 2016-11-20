package xyz.jameskr.fm;

import xyz.jameskr.fm.schedule.FlightSchedule;

public class Main {
    public static void main(String[] args) {
        System.out.println("JFlightManager. v1.0.0");
        FlightSchedule schedule = new FlightSchedule();
        schedule.run();
    }
}
