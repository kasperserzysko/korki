package com.korki.backend.dtos.teacher_dtos;

import com.korki.common.models.enums.Education;
import com.korki.common.models.enums.Experience;
import com.korki.common.models.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;

@Data
@NoArgsConstructor
public class TeacherCredentialsDto {

    @NotNull
    @NotBlank
    private String firstName;
    private String lastName;

    @NotNull
    private Gender gender;
    private LocalDate birthday;
    private Education education;
    private Experience experience;
    private Year yearOfCareerStart;

    //ADDRESS AND CONTACTS
    private String voivodeship;
    private String city;
    private String district;
    private String phoneNumber;

    private String email;
    private String password;
}
