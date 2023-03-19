package com.korki.backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCredentialsDto {

    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Must be an email!")
    private String email;

    @NotBlank(message = "Password is mandatory!")
    private String password;
}
