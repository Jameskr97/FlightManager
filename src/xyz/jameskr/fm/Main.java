package xyz.jameskr.fm;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to JFlightManager. By James ");
        ConsoleMenu menu = new ConsoleMenu(MainMenuOptions.values());


        while(true){
            menu.printOptions();
            int x = menu.getResponse();
            if (x == MainMenuOptions.SET_CLOCK.getID()){
                System.out.println("Option not implemented yet.");

            } else if (x == MainMenuOptions.RESET_SCHEDULE.getID()){
                System.out.println("Option not implemented yet.");

            } else if (x == MainMenuOptions.ADD_AIRLINE.getID()){
                System.out.println("Option not implemented yet.");

            } else if (x == MainMenuOptions.ADD_FLIGHT.getID()){
                System.out.println("Option not implemented yet.");

            } else if (x == MainMenuOptions.CANCEL_FLIGHT.getID()){
                System.out.println("Option not implemented yet.");

            } else if (x == MainMenuOptions.SHOW_FLIGHT_INFO.getID()){
                System.out.println("Option not implemented yet.");

            } else if (x == MainMenuOptions.SHOW_DEPART.getID()){
                System.out.println("Option not implemented yet.");

            } else if (x == MainMenuOptions.SHOW_ARRIVAL.getID()){
                System.out.println("Option not implemented yet.");

            } else if(x == MainMenuOptions.EXIT.getID()){
                System.out.println("Saving...");
                System.out.println("Thanks for using JFlightManager");
                System.exit(0);
            }
        }


    }



}