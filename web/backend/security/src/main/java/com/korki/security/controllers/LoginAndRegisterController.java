package com.korki.security.controllers;

import com.korki.security.models.UserRegisterDto;
import com.korki.security.services.LoginAndRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginAndRegisterController {

    private final LoginAndRegisterService loginAndRegisterService;

    public LoginAndRegisterController(LoginAndRegisterService loginAndRegisterService) {
        this.loginAndRegisterService = loginAndRegisterService;
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody UserRegisterDto dto){
        loginAndRegisterService.registerUser(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
