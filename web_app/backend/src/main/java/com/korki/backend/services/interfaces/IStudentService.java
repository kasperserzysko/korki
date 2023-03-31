package com.korki.backend.services.interfaces;

import com.korki.backend.dtos.user_dtos.SecurityUser;

public interface IStudentService {

    void applyForAdvert(Long advertId, SecurityUser loggedUser);
}
