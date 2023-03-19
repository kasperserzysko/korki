package com.korki.backend.services.interfaces;


import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.UserCredentialsDto;
import com.korki.backend.dtos.UserDetailsDto;
import com.korki.backend.exceptions.AccountNotEnabledException;

public interface IAuthenticationService {

    void register(UserCredentialsDto dto);
    String login(UserCredentialsDto dto) throws AccountNotEnabledException, Exception;
    void activate(String activationUrl);
    void fillCredentials(UserDetailsDto dto, SecurityUser user);
}
