package com.korki.backend.services.interfaces;

import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.TeacherCredentialsDto;

public interface ITeacherService {

    void updateProfile(TeacherCredentialsDto dto, SecurityUser loggedUser) throws Exception;
    TeacherCredentialsDto getTeacherCredentials(SecurityUser loggedUser);
}
