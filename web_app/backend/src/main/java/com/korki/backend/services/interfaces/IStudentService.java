package com.korki.backend.services.interfaces;

import com.korki.backend.dtos.SecurityUser;

public interface IStudentService {

    void applyForAdvert(Long advertId, SecurityUser loggedUser);
}
