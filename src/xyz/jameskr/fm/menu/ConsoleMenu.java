package xyz.jameskr.fm.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Console menu class which manages selection and presentation of menu items
 *
 * @author James 
 * @date 11/12/16
 */
public class ConsoleMenu {

    /**
     * Menu options in the order they are displayed.
     */
    private ArrayList<String> menuOptions;
    private Scanner input;
    private String header;


    /**
     * ConsoleMenu default constructor.
     */
    public ConsoleMenu() {
        menuOptions = new ArrayList<>();
        input = new Scanner(System.in);
        this.header = "Enter a number to select your choice.";
    }

    /**
     * Constructor which takes in array of main menu options
     * FIXME: This constructor is horribly implemented, and it's implementation, along with MainMenuOptions must be changed.
     *
     * @param options MainMenuOptions array
     */
    public ConsoleMenu(String[] options) {
        this(); // Calls the above constructor.
        Collections.addAll(menuOptions, options);
    }

    public ConsoleMenu(String header) {
        this();
        this.header = header;
    }

    /**
     * Add menu option to array
     *
     * @param index  index of menu option
     * @param option text of menu option
     */
    public void addOption(int index, String option) {
        this.menuOptions.add(index, option);
    }

    /**
     * Print all the menu options with their corresponding selection number
     */
    public void printOptions() {
        System.out.printf("\n%s\n=====================================\n", header);
        for (int i = 0; i < menuOptions.size(); i++) {
            System.out.println(String.format("%d: %s", i + 1, menuOptions.get(i)));
        }
    }

    /**
     * Get the number relating to tne menu option.
     *
     * @return Integer of answer
     */
    public int getResponse() {
        int res = -1;
        do {
            System.out.print("↦ ");
            String in = input.nextLine();

            try {
                res = Integer.parseInt(in);
                res--; // Data is 0 indexed, user entered index starting at 1. Subtract to match data.
            } catch (Exception e) {
            }

            if (res < 0 || res > menuOptions.size() - 1) // Make sure the answer is withing what we want.
                res = -1;

            if (res == -1) printOptions();
        } while (res == -1); // Keep doing the above if the answer is not what we want.
        System.out.println(); // New line for spacing
        return res;
    }

}
