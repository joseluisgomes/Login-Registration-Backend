package com.example.demo.registration;

import com.example.demo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final AppUserService appUserService;

    public String registerAppUser(@RequestBody RegistrationRequest registrationRequest) {
        return appUserService.registerAppUser(registrationRequest);
    }
}
