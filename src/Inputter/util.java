/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inputter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ADMIN
 */
public class util {

    public static final Scanner sc = new Scanner(System.in);

    /**
     * get a char match the regular expression
     *
     * @param msg
     * @param regex
     * @return
     */
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

    /**
     * normalize string
     *
     * @param S
     * @return normalized string
     */
    // Normalize string: "  i love   you" -> "i love you"
    public static String normalize(String S) {
        StringTokenizer stk = new StringTokenizer(S, " ");
        String result = stk.nextToken();
        while (stk.hasMoreElements()) {
            result += " " + stk.nextToken();
        }
        return result;
    }

    /**
     * input non blank string
     *
     * @param msg
     * @return string
     */
    public static String getNonBlankStr(String msg) {
        // get a non blank string
        String result;
        do {
            System.out.print(msg);
            result = normalize(sc.nextLine()); //normalizing
        } while (result.length() == 0);
        return result;
    }

    /**
     * validate string which matches the regular expression
     *
     * @param msg
     * @param regEx
     * @return string
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

    /**
     * The expression will append a comma after all digits that are followed by
     * at least one group of 3 digits.
     *
     * @param i
     * @return string formatted
     */
    public static String thousandSeparator(int i) {

        String formatted = Integer.toString(i).replaceAll("(\\d)(?=(\\d{3})+$)", "$1,");
        return (formatted);
    }

    /**
     * sum of digit
     *
     * @param n
     * @return sum
     */
    public static int sumOfDigit(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n = n / 10;
        }
        return sum;
    }

    /**
     * sum of digit
     *
     * @param word
     * @return sum
     */
    public static int sumOfDigit(String word) {
        int sum = 0;
        int n = Integer.parseInt(word);
        while (n != 0) {
            sum += n % 10;
            n = n / 10;
        }
        return sum;
    }

    /**
     * check prime
     *
     * @param n
     * @return true if it is prime
     */
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        } else if (n <= 3) {
            return true;
        }
        //n = 6k+i with i from -1 to 4
        //but i = 0 2 3 4 guarantees a composite number
        //so we check if 2 3 devides n then check 6k±1 upto sqrt(n)
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * reverse only a word
     *
     * @param word
     * @return reversed word
     */
    public static String reverseWord(String word) {
        String rev = "";
        char c;
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            rev = c + rev;
        }
        return rev;
    }

    /**
     * reverse the whole string
     *
     * @param str
     * @return string
     */
    public static String reverseSentence(String str) {
        StringBuilder sbr = new StringBuilder(str);
        sbr.reverse();
        return sbr.toString();
    }

    /**
     * reverse word order in string
     *
     * @param str
     * @return string
     */
    public static String reverseWordInString(String str) {
        String[] s = str.split("\\s");
        int l = s.length;
        if (l % 2 == 0) {
            int j = l / 2; //find middle
            //start from middle and swapping j and l-1-j
            while (j <= l - 1) {
                String temp;
                temp = s[l - j - 1];
                s[l - j - 1] = s[j];
                s[j] = temp;
                j += 1;
            }
        } else {
            int j = (l / 2) + 1;
            //same as above;
            while (j <= l - 1) {
                String temp;
                temp = s[l - j - 1];
                s[l - j - 1] = s[j];
                s[j] = temp;
                j += 1;
            }
        }
        return String.join(" ", s);
    }

    /**
     * make array of char become string
     *
     * @param s char[] s
     * @return string
     */
    public static String arrayToStr(char[] s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        return sb.toString();
    }

    /**
     * remove digits from word
     *
     * @param word
     * @return word with only letter
     */
    public static String getCharsOnly(String word) {
        word = word.replaceAll("[0-9]", "");
        return word;
    }

    /**
     * remove all chars that are not either space or digit
     *
     * @param word
     * @return
     */
    public static String getDigitsOnly(String word) {
        return word.replaceAll("[^ 0-9]", "");
    }

    /**
     * round number to n places after dot
     *
     * @param value
     * @param places
     * @return rounded double
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * return the number of digits of an int using log10
     *
     * @param number
     * @return number of digits
     */
    public static int numberOfDigits(int number) {
        return (int) (Math.log10(number) + 1);
    }

    /**
     * check if char is vowel
     *
     * @param ch
     * @return true if it is vowel
     */
    public static boolean isVowel(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U');
    }

    /**
     * count total number of vowel
     *
     * @param str
     * @return total number of vowel
     */
    public static int countVowels(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (isVowel(str.charAt(i))) {
                ++count;
            }

        }
        return count;
    }

    /**
     * if reverse string equal itself
     *
     * @param str
     * @return boolean
     */
    public static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(i)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    /**
     * reverse only digit
     * @param str
     * @return string
     */
    public static String reverseOnlyDigits(String str) {
        //the pattern will match a sequence of 1 or more digits
        Matcher matcher = Pattern.compile("\\d+").matcher(str);
        //fetch the pos of the next sequence of digits
        if (!matcher.find()) {
            return str;
        }
        //keep everything before the number
        String pre = str.substring(0, matcher.start());
        //reverse number
        String number = matcher.group();
        number = new StringBuilder(number).reverse().toString();
        return pre + number + reverseOnlyDigits(str.substring(matcher.end()));
    }
    /**
     * print binary
     * @param num 
     */
    public static void decimalToBin(int num) {
        int[] binary = new int[35];
        int id = 0;
        
        while(num > 0) {
            binary[id++] = num % 2;
            num = num / 2;
        }
        for (int i = id - 1; i >= 0; i--) {
            System.out.print(binary[i] + "");
        }
    }
    public static String Translate(String text) throws IOException {
        if (text.length() == 0) return text;
        URL url = new URL("https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=vi&dt=t&q="
                + text.replace(" ", "%20"));
        URLConnection urlConn = url.openConnection();
        urlConn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US)");
        Scanner sc = new Scanner(urlConn.getInputStream());
        String ans = "";
        while (sc.hasNext()) {
            ans = ans + sc.nextLine();
        }
        int first = ans.indexOf('"') + 1;
        int second = ans.indexOf('"', first);
        ans = ans.substring(first, second);
        return ans;
    }
    public static void main(String[] args) {
        try {
            System.out.println(util.Translate("hello"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
