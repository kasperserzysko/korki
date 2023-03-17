package com.korki.backend.models;

import lombok.Data;

@Data
public class UserCredentialsDto {
    private String email;
    private String password;
}
