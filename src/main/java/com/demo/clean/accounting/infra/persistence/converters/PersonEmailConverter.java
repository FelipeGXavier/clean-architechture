package com.demo.clean.accounting.infra.persistence.converters;

import com.demo.clean.accounting.domain.PersonEmail;

import javax.persistence.AttributeConverter;

public class PersonEmailConverter implements AttributeConverter<PersonEmail, String> {
    @Override
    public String convertToDatabaseColumn(PersonEmail personEmail) {
        return personEmail == null ? null : personEmail.toString();
    }

    @Override
    public PersonEmail convertToEntityAttribute(String email) {
        return PersonEmail.of(email);
    }
}
