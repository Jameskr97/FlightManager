package xyz.jameskr.fm.schedule;

/**
 * @author James 
 * @date 11/12/16
 */
public class Aircraft {

    public static Aircraft DEFAULT_AIRCRAFT = new Aircraft();

    private String model;
    private int cargoCapacity; //square feet
    private int passengerCapacity; // people

    public Aircraft(){
        this.model = "Boeing 777-9";
        this.cargoCapacity = 5330;
        this.passengerCapacity =  400;
    }

    public String getName() {
        return model;
    }

    @Override
    public String toString() {
        return String.format("Aircraft: %s\nCargo Capacity: %d\nMax Passengers: %d", model, cargoCapacity, passengerCapacity);
    }
}
