package com.demo.clean.accounting.domain;

public class PersonEmail {

    private String email;
    private final String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";

    private PersonEmail(String email) {
        if (!email.matches(email)) {
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
