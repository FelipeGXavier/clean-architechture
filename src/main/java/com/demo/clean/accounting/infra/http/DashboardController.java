package com.demo.clean.accounting.infra.http;

import com.demo.clean.accounting.application.UserInformation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/dashboard")
public class DashboardController {

    private final UserInformation userInformationUseCase;

    public DashboardController(UserInformation userInformationUseCase) {
        this.userInformationUseCase = userInformationUseCase;
    }

    @GetMapping("/{user}")
    public ResponseEntity<?> dashboardInfo(@PathVariable Long user) {
        return new ResponseEntity<>(this.userInformationUseCase.execute(user), HttpStatus.OK);
    }

}
