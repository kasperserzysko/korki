package com.korki.backend.controllers;

import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.TeacherCredentialsDto;
import com.korki.backend.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class TeacherController {

    private final TeacherService teacherService;

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PutMapping("/edit")
    public void updateTeacher(@RequestBody TeacherCredentialsDto dto, @AuthenticationPrincipal SecurityUser loggedUser) throws Exception {
        teacherService.updateProfile(dto, loggedUser);
    }

    //@PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/edit")
    public ResponseEntity<TeacherCredentialsDto> getEditData(@AuthenticationPrincipal SecurityUser loggedUser){
        return ResponseEntity.ok(teacherService.getTeacherCredentials(loggedUser));
    }




    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(Exception.class)
    public String handleExceptions(Exception ex){
        return ex.getMessage();
    }


}
