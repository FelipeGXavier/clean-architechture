package com.demo.clean.shared;

import com.demo.clean.person.domain.Person;
import com.demo.clean.person.infra.persistence.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadLoggedUser {

    private static PersonRepository personRepository;

    @Autowired
    public LoadLoggedUser(PersonRepository repository) {
        personRepository = repository;
    }

    public static Person load(Long id){
        return personRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User not found")
        );
    }
}
