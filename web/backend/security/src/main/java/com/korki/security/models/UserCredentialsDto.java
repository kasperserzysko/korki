package com.korki.security.models;

import lombok.Data;

@Data
public class UserCredentialsDto {
    private String email;
    private String password;
}
