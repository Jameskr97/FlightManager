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
     * Method for waiting for user to press enter. Mainly so scanner doesn't have to be imported several places
     * for one purpose.
     */
    public static void safeWait() {
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }
}
