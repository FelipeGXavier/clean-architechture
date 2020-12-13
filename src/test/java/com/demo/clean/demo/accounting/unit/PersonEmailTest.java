package com.demo.clean.demo.accounting.unit;

import com.demo.clean.accounting.domain.PersonEmail;
import com.demo.clean.shared.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonEmailTest {

    @Test
    @DisplayName("Valid e-mail for user")
    public void validEmail() {
        var email = "foo@gmail.com";
        assertEquals(PersonEmail.of(email).toString().length(), email.length());
    }

    @Test
    @DisplayName("Invalid e-mail for user")
    public void invalidEmail() {
        var email = "foobar.com";
        var exception = Assertions.assertThrows(DomainException.class, () -> PersonEmail.of(email));
        assertEquals(exception.getMessage(), "Invalid email");
    }

    @Test
    @DisplayName("Null e-mail for user")
    public void nullEmail() {
        String email = null;
        var exception = Assertions.assertThrows(DomainException.class, () -> PersonEmail.of(email));
        assertEquals(exception.getMessage(), "Invalid email");
    }
}
