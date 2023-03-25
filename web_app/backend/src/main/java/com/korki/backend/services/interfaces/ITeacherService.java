package com.korki.backend.services.interfaces;

import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.TeacherCredentialsDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ITeacherService {

    void updateProfile(TeacherCredentialsDto dto, SecurityUser loggedUser) throws Exception;
    TeacherCredentialsDto getTeacherCredentials(SecurityUser loggedUser);
}
