package com.example.jezuz1n.hairly.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jsalas on 28/4/17.
 */

public class ValidatorUtil {
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(String password){
        return password.length()>7;
    }

}
