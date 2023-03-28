package com.korki.backend.utills.mappers;

import com.korki.backend.dtos.UserCredentialsDto;
import com.korki.backend.utills.Validator;
import com.korki.common.models.User;
import com.korki.common.models.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final Validator validator;

    public void mapNewUserCredentialsToEntity(UserCredentialsDto dto, User user, PasswordEncoder passwordEncoder, Role role){
        validator.validate(dto);

        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);
    }

}
