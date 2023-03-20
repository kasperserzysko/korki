package com.korki.backend.dtos;

import com.korki.backend.annotations.MappingField;
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
    @MappingField(fieldName = "firstName")
    private String firstName;
    @MappingField(fieldName = "lastName")
    private String lastName;

    @NotNull
    @NotBlank
    @MappingField(fieldName = "gender")
    private Gender gender;
    @MappingField(fieldName = "birthday")
    private LocalDate birthday;
    @MappingField(fieldName = "education")
    private Education education;
    @MappingField(fieldName = "experience")
    private Experience experience;
    @MappingField(fieldName = "yearOfCareerStart")
    private Year yearOfCareerStart;

    //ADDRESS AND CONTACTS
    @MappingField(fieldName = "voivodeship")
    private String voivodeship;
    @MappingField(fieldName = "city")
    private String city;
    @MappingField(fieldName = "district")
    private String district;
    @MappingField(fieldName = "phoneNumber")
    private String phoneNumber;

    private String email;
    private String password;

}
