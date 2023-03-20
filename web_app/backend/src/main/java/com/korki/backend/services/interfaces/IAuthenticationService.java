package com.korki.backend.services.interfaces;


import com.korki.backend.dtos.UserCredentialsDto;
import com.korki.common.models.enums.Role;

public interface IAuthenticationService {

    void register(UserCredentialsDto dto, Role role);
    String login(UserCredentialsDto dto) throws Exception;
    void activate(String activationUrl);
}
