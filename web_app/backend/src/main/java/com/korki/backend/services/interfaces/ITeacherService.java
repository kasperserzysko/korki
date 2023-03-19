package com.korki.backend.services.interfaces;

import com.korki.backend.dtos.AdvertCreateDto;
import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.TeacherDetailsDto;

import java.io.IOException;

public interface ITeacherService {
    void addAdvert(AdvertCreateDto dto, SecurityUser loggedUser);
    void updateTeacher(TeacherDetailsDto dto, SecurityUser loggedUser) throws IOException;
}
