package com.korki.security.controllers;

import com.korki.security.models.UserCredentialsDto;
import com.korki.security.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public void register(@RequestBody UserCredentialsDto dto) {
     authenticationService.register(dto);
  }
  @PostMapping("/login")
  public String login(@RequestBody UserCredentialsDto dto) {
    return authenticationService.login(dto);
  }


}
