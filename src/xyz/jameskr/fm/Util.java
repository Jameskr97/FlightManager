package xyz.jameskr.fm;

import java.util.Scanner;

/**
 * Utility class. For functions that can be use almost anywhere.
 *
 * @author James 
 * @date 11/13/16
 */
public class Util {

    /**
     * Method for getting a line of input where creating a full
     * scanner implementation is excessive.
     *
     * @return Input String
     */
    public static String getInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    /**
     * Method for waiting for user to press enter. Same as above function, except doesn't return anything.
     */
    public static void safeWait() {
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }

}
