package com.korki.backend.services.interfaces;

import com.korki.backend.dtos.user_dtos.SecurityUser;
import com.korki.backend.dtos.teacher_dtos.TeacherCredentialsDto;
import com.korki.backend.dtos.teacher_dtos.TeacherDisplayDto;
import com.korki.backend.exceptions.EmailFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ITeacherService {

    TeacherDisplayDto getTeacherProfileDisplay(SecurityUser loggedUser);
    void updateProfile(TeacherCredentialsDto dto, MultipartFile file, SecurityUser loggedUser) throws EmailFoundException, IOException;
    TeacherCredentialsDto getTeacherCredentials(SecurityUser loggedUser);
    byte[] getProfileImage(SecurityUser loggedUser) throws IOException;

}
