package org.example.utils;

public class Utils {
    public static boolean checkDouble(String input) {
        String sanitizedInput = input.trim().replaceAll(",", ".");
        String regex = "\\d+(\\.\\d+)?";
        if (sanitizedInput.matches(regex))
            try {
                Double.parseDouble(sanitizedInput);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        return false;
    }

    public static boolean checkInt(String input) {
        String regex = "\\d+";
        if (input.matches(regex)) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
