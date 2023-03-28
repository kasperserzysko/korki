package com.korki.backend.dtos.teacher_dtos;

import com.korki.common.models.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherDisplayDto {

    private String firstName;
    private Gender gender;
}
