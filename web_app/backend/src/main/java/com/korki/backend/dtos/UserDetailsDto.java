package com.korki.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.korki.backend.annotations.DtoFor;
import com.korki.backend.annotations.MappingField;
import com.korki.common.models.User;
import com.korki.common.models.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DtoFor(classType = User.class)
public class UserDetailsDto {

    @JsonProperty(value = "first_name")
    @NotBlank(message = "First name field can't be blank!")
    @MappingField(fieldName = "firstName")
    private String firstName;

    @JsonProperty(value = "last_name")
    @NotBlank(message = "Last name field can't be blank!")
    @MappingField(fieldName = "lastName")
    private String lastName;

    @JsonProperty(value = "phone_number")
    @Size(min = 8, max = 12)
    @MappingField(fieldName = "phoneNumber")
    private String phoneNumber;

    @NotNull(message = "You have to select user type!")
    @MappingField(fieldName = "role")
    private Role role;
}
