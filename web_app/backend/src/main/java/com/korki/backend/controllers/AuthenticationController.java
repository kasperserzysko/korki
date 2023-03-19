package com.korki.backend.controllers;

import com.korki.backend.exceptions.AccountNotEnabledException;
import com.korki.backend.dtos.UserCredentialsDto;
import com.korki.backend.services.AuthenticationService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PermitAll
  @PostMapping("/register")
  public void register(@RequestBody UserCredentialsDto dto) {
     authenticationService.register(dto);
  }

  @PermitAll
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
  @PermitAll
  @GetMapping("activation/{url}")
  public ResponseEntity activate(@PathVariable("url") String url){
    try {
      authenticationService.enableAccount(url);
    }catch (UsernameNotFoundException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/hi")
  public String hi(){
    return "hi";
  }
}
