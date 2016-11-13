package xyz.jameskr.fm.menu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by James on 11/12/16.
 */
public class ConsoleMenu {

    private ArrayList<String> menuOptions;
    private Scanner input;


    /**
     * ConsoleMenu default constructor.
     */
    public ConsoleMenu(){
        menuOptions = new ArrayList<>();
        input = new Scanner(System.in);
    }

    /**
     *
     * @param options MainMenuOptions array
     */
    public ConsoleMenu(MainMenuOptions[] options) {
        this(); // Calls the above constructor.
        for (MainMenuOptions opt : options)
            menuOptions.add(opt.getID(), opt.getDescription());
    }

    public void addOption(String option) {
        this.menuOptions.add(option);
    }

    /**
     * Print all the menu options with their corresponding selection number
     */
    public void printOptions(){
        System.out.println("\nEnter a number to select your choice.\n=====================================");
        for (int i = 0; i < menuOptions.size(); i++){
            System.out.println(String.format("%d: %s", i + 1, menuOptions.get(i)));
        }
    }

    /**
     * Get the number relating to tne menu option.
     *
     * @return Integer of answer
     */
    public int getResponse(){
        int res = -1;
        do {
            System.out.print("↦ ");
            String in = input.nextLine();

            try {
                res = Integer.parseInt(in);
                res--; // Data is 0 indexed, user entered index starting at 1. Subtract to match data.
            } catch (Exception e){}

            if (res < 0 || res > menuOptions.size() - 1) // Make sure the answer is withing what we want.
                res = -1;

            if (res == -1) printOptions();
        } while (res == -1); // Keep doing the above if the answer is not what we want.
        System.out.println(); // New line for spacing
        return res;
    }

}
