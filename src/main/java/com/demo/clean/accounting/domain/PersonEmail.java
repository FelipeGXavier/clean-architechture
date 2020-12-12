package com.demo.clean.accounting.domain;

import java.util.regex.Pattern;

public class PersonEmail {

    private String email;
    private final String regex = "^(.+)@(.+)$";

    private PersonEmail(String email) {
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(email == null ? "" : email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    public static PersonEmail of(String email) {
        return new PersonEmail(email);
    }

    @Override
    public String toString() {
        return email;
    }
}
