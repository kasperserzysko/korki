package com.korki.backend.controllers;

import com.korki.backend.dtos.UserCredentialsDto;
import com.korki.backend.services.AuthenticationService;
import com.korki.common.models.enums.Role;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PermitAll
  @PostMapping("/register_teacher")
  public ResponseEntity<?> registerTeacher(@RequestBody UserCredentialsDto dto) {
    authenticationService.register(dto, Role.ROLE_TEACHER);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  @PermitAll
  @PostMapping("/register_student")
  public ResponseEntity<?> registerStudent(@RequestBody UserCredentialsDto dto) {
    authenticationService.register(dto, Role.ROLE_STUDENT);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PermitAll
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserCredentialsDto dto) throws Exception {
      return ResponseEntity.ok(authenticationService.login(dto));
      //return new ResponseEntity<>(HttpStatus.OK);
  }
  @PermitAll
  @GetMapping("activation/{url}")
  public ResponseEntity<?> activate(@PathVariable("url") String url){
    authenticationService.activate(url);
    return new ResponseEntity<>(HttpStatus.OK);
  }



  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public List<String> handleValidationExceptions(ConstraintViolationException ex) {
    return ex.getConstraintViolations()
            .stream()
            .map(ConstraintViolation::getMessage).toList();
  }
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UsernameNotFoundException.class)
  public String handleUsernameNotFoundExceptions(UsernameNotFoundException ex){
    return ex.getMessage();
  }

  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  @ExceptionHandler(DisabledException.class)
  public String handleDisabledExceptions(DisabledException ex){
    return ex.getMessage();
  }
}
