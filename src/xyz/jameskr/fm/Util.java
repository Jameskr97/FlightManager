package xyz.jameskr.fm;

import java.util.Scanner;

/**
 * @author James 
 * @date 11/13/16
 */
public class Util {

    /**
     *
     * Method for getting a line of input where creating a full
     * scanner implementation is excessive.
     *
     * @return Input String
     */
    public static String getInput(){
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }
}
