package com.korki.backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCredentialsDto {

    @NotNull
    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Must be an email!")
    private String email;

    @NotNull
    @NotBlank(message = "Password is mandatory!")
    private String password;

    @NotNull
    @NotBlank
    private String firstName;
}
