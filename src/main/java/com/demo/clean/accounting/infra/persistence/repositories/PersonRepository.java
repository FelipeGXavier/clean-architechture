package com.demo.clean.accounting.infra.persistence.repositories;

import com.demo.clean.accounting.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
