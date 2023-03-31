package com.korki.backend.dtos.rating_dtos;

import com.korki.backend.dtos.user_dtos.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RatingDto {

    private String comment;
    private LocalDate date;
    private int rating;
    private UserDto user;
}
