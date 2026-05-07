/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Hashir
 */

import java.util.regex.Pattern;

public class ValidationUtils {

    // Check if a string is null or empty after trimming
    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.trim().isEmpty());
    }

    // Validate email format
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) return false;
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }

    // Check if a string contains only digits (for numeric validation)
    public static boolean isNumeric(String str) {
        if (isNullOrEmpty(str)) return false;
        return str.matches("\\d+");
    }

    // Validate phone number (example: digits only, length 10-15)
    public static boolean isValidPhoneNumber(String phone) {
        if (isNullOrEmpty(phone)) return false;
        return phone.matches("\\d{10,15}");
    }
}


