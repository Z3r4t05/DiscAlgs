/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inputter;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author ADMIN
 */
public class util {

    public static final Scanner sc = new Scanner(System.in);

    public static char getChar(String msg, String regex) {
        char c;
        System.out.print(msg);
        boolean esc = false;
        String s;
        do {
            try {
                s = sc.next();
                if (s.length() > 1) {
                    throw new RuntimeException("Input too long!");
                }
                c = s.charAt(0);
                if (!Character.isLetter(c)) {
                    throw new RuntimeException("Input must be a letter!");
                }
                if (!s.matches(regex)) {
                    throw new RuntimeException("Invalid input!");
                }
                return c;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                return getChar(msg, regex);
            }
        } while ((s.length() == 0));
    }

    public static void main(String[] args) {

    
    }

    // Normalize string: "  i love   you" -> "i love you"
    public static String normalize(String S) {
        StringTokenizer stk = new StringTokenizer(S, " ");
        String result = stk.nextToken();
        while (stk.hasMoreElements()) {
            result += " " + stk.nextToken();
        }
        return result;
    }

    public static String getNonBlankStr(String msg) {
        // get a non blank string
        String result;
        do {
            System.out.print(msg);
            result = normalize(sc.nextLine()); //normalizing
        } while (result.length() == 0);
        return result;
    }

    /*
    regular expression: [pP][\\d]{3} for p000, P001, P909,...
    -[pP]: one char and it must be either p or P
    -[\\d]{3} means 3 digits
     */
    public static String getPatternStt(String msg, String regEx) {
        String result;
        do {
            System.out.print(msg);
            result = normalize(sc.nextLine()); //normalizing
        } while (!result.matches(regEx));
        return result;
    }

    public static int getintGreater(String msg, int min) {
        // get an int which is greater than min
        int result;
        try {
            do {
                System.out.print(msg);
                result = Integer.parseInt(sc.nextLine());
            } while (result < min);
            return result;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return getintGreater(msg, min);
        }
    }

    /**
     * Integer input validation
     *
     * @param msg input message
     * @param min min value
     * @param max max value
     * @return an integer in range min and max
     * @since 2021-11-04
     */
    public static int getintMinMax(String msg, int min, int max) {
        // get an int which is greater than min
        int result;

        try {
            do {
                System.out.print(msg);
                result = Integer.parseInt(sc.nextLine());
            } while (result < min || result > max);
            return result;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return getintMinMax(msg, min, max);
        }
    }
    
    public static String thousandSeparator(int i) {
        //The expression will append a comma after all digits that are followed by at least one group of 3 digits.
        String formatted = Integer.toString(i).replaceAll("(\\d)(?=(\\d{3})+$)", "$1,");
        return (formatted);
    }
   
}
