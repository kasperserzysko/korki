package com.korki.backend.controllers;

import com.korki.backend.models.UserCredentialsDto;
import com.korki.backend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public void register(@RequestBody UserCredentialsDto dto) {
     authenticationService.register(dto);
  }
  @PostMapping("/login")
  public void login(@RequestBody UserCredentialsDto dto) {
    authenticationService.login(dto);
  }


}
