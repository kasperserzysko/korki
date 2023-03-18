package com.korki.backend.controllers;

import com.korki.backend.exceptions.AccountNotEnabledException;
import com.korki.backend.models.UserCredentialsDto;
import com.korki.backend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
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
  public ResponseEntity login(@RequestBody UserCredentialsDto dto){
    try {
      authenticationService.login(dto);
    } catch (AccountNotEnabledException e) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    } catch (AuthenticationException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
