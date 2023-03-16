package com.korki.security.auth;

import com.korki.security.models.UserCredentialsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sec")
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
