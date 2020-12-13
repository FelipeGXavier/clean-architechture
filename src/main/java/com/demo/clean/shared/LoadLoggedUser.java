package com.demo.clean.shared;

import com.demo.clean.accounting.domain.Person;
import com.demo.clean.accounting.infra.persistence.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadLoggedUser {

    private static PersonRepository personRepository;

    @Autowired
    public LoadLoggedUser(PersonRepository repository) {
        personRepository = repository;
    }

    /** Simulate a login-like feature * */
    public static Person load(Long id) {
        return personRepository
                .findById(id)
                .orElseThrow(() -> new DomainException("User not found"));
    }
}
