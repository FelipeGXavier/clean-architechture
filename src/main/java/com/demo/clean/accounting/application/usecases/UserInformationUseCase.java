package com.demo.clean.accounting.application.usecases;

import com.demo.clean.accounting.adapter.DashboardResponse;
import com.demo.clean.accounting.adapter.presenter.UserDashboardPresenter;
import com.demo.clean.accounting.application.UserInformation;
import com.demo.clean.accounting.infra.persistence.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationUseCase implements UserInformation {

    private final PersonRepository personRepository;
    private UserDashboardPresenter presenter;


    @Autowired
    public UserInformationUseCase(PersonRepository personRepository, UserDashboardPresenter presenter) {
        this.personRepository = personRepository;
        this.presenter = presenter;
    }

    @Override
    public DashboardResponse execute(Long personId) {
        var person = this.personRepository.findById(personId);
        return this.presenter.present(person);
    }
}
