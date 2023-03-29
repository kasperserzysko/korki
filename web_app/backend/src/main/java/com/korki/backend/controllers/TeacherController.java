package com.korki.backend.controllers;

import com.korki.backend.dtos.AdvertDetailsDto;
import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.advert_dtos.AdvertDisplayDto;
import com.korki.backend.dtos.teacher_dtos.TeacherCredentialsDto;
import com.korki.backend.dtos.teacher_dtos.TeacherDisplayDto;
import com.korki.backend.exceptions.EmailFoundException;
import com.korki.backend.exceptions.NoAccessException;
import com.korki.backend.exceptions.NotFoundException;
import com.korki.backend.services.AdvertService;
import com.korki.backend.services.TeacherService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class TeacherController {

    private final TeacherService teacherService;
    private final AdvertService advertService;

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PutMapping("/edit")
    public ResponseEntity<?> updateTeacher(@RequestPart("teacher") TeacherCredentialsDto dto, @RequestPart("image") MultipartFile file, @AuthenticationPrincipal SecurityUser loggedUser) throws EmailFoundException, IOException {
        teacherService.updateProfile(dto, file, loggedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/edit")
    public ResponseEntity<TeacherCredentialsDto> getEditCredentials(@AuthenticationPrincipal SecurityUser loggedUser){
        return ResponseEntity.ok(teacherService.getTeacherCredentials(loggedUser));
    }
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping
    public ResponseEntity<TeacherDisplayDto> getUserProfile(@AuthenticationPrincipal SecurityUser loggedUser){
        return ResponseEntity.ok(teacherService.getTeacherProfileDisplay(loggedUser));
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/adverts")
    public ResponseEntity<List<AdvertDisplayDto>> getProfileAdverts(@AuthenticationPrincipal SecurityUser loggedUser){
        return ResponseEntity.ok(advertService.getProfileAdverts(loggedUser));
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping("/adverts")
    public ResponseEntity<?> createAdvert(@RequestBody AdvertDetailsDto dto, @AuthenticationPrincipal SecurityUser loggedUser){
        advertService.createAdvert(dto, loggedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PutMapping("/adverts/{id}")
    public ResponseEntity<?> updateAdvert(@RequestBody AdvertDetailsDto dto, @PathVariable("id") Long id) throws NotFoundException {
        advertService.updateAdvert(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/adverts/{id}")
    public ResponseEntity<AdvertDetailsDto> getEditCredentials(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(advertService.getAdvertEditDetails(id));
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @DeleteMapping("/adverts/{id}")
    public ResponseEntity<?> deleteAdvert(@PathVariable("id") Long id, @AuthenticationPrincipal SecurityUser loggedUser) throws NoAccessException, NotFoundException {
        advertService.deleteAdvert(id, loggedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    //////////////////////////////EXCEPTION HANDLERS//////////////////////////////

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NoAccessException.class)
    public String handleNoAccessException(NoAccessException ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<String> handleValidationExceptions(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage).toList();
    }
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(EmailFoundException.class)
    public String handleExceptions(EmailFoundException ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(IOException.class)
    public String handleIOException(){
        return "Couln't upload a file!";
    }
}
