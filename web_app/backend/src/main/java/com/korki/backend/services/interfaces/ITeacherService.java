package com.korki.backend.services.interfaces;

import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.teacher_dtos.TeacherCredentialsDto;
import com.korki.backend.dtos.teacher_dtos.TeacherDisplayDto;
import org.springframework.web.multipart.MultipartFile;

public interface ITeacherService {

    TeacherDisplayDto getTeacherProfileDisplay(SecurityUser loggedUser);

    void updateProfile(TeacherCredentialsDto dto, MultipartFile file, SecurityUser loggedUser) throws Exception;

    TeacherCredentialsDto getTeacherCredentials(SecurityUser loggedUser);
}
