package com.demo.clean.person.infra.persistence.converters;

import com.demo.clean.person.domain.PersonEmail;

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
