package com.korki.backend.dtos;

import lombok.Data;

@Data
public class UserCredentialsDto {
    private String email;
    private String password;
}
