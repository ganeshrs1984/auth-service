package com.enterprise.auth.controller;

import com.enterprise.auth.dao.UserDaoService;
import com.enterprise.auth.dto.Auth;
import com.enterprise.auth.dto.Registration;
import com.enterprise.auth.entity.User;
import com.enterprise.auth.service.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AuthController {

    @Autowired
    private UserDaoService userDaoService;
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/auth/user/authenticate")
    public ResponseEntity<String> auth(@RequestBody Auth auth) throws JsonProcessingException {
        User token = userDaoService.findByEmailAndPassword(auth.getUsername(),auth.getPassword());
        if(token != null){
            return ResponseEntity.status(HttpStatus.OK).body(UUID.randomUUID().toString());
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("NA");
        }
    }

    @PostMapping("/auth/user/register")
    public ResponseEntity<Void> register(@RequestBody Registration registration) throws JsonProcessingException {
        User user = new User();
        user.setEmail(registration.getEmail());
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setPassword(registration.getPassword());
        registrationService.register(user);
        return (ResponseEntity<Void>) ResponseEntity.status(HttpStatus.CREATED);
    }
}
