package com.smartosc.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by smartosc on 5/5/2016.
 */
public class EmailValidator {
    private Matcher matcher;
    private Pattern pattern;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final EmailValidator instance = new EmailValidator();

    private EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public static EmailValidator getInstance() {
        return instance;
    }

    public boolean isValid(final String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
