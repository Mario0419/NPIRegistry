package Mx3Utils;

/**
 * Created by mario on 4/10/2016.
 */
public class FormatUtils {
    public static String formatPhone(String number) {
        String result = number;

        if(number.length() == 10) {
            result = "(" + number.substring(0,3) + ") " + number.substring(3,6) + "-" + number.substring(6);
        }
        return result;
    }

    public static String formatZipCode(String zip) {
        String result = zip;

        if(zip.length() == 9) {
            result = zip.substring(0, 5) + "-" + zip.substring(5);
        }

        return result;
    }
}
