package com.demo.clean.person.domain;

import com.demo.clean.person.infra.persistence.converters.PersonEmailConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = PersonEmailConverter.class)
    private PersonEmail email;

    private String login;
    private String password;

    public Person(PersonEmail email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
