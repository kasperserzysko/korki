package com.korki.backend.dtos.advert_dtos;

import com.korki.backend.dtos.teacher_dtos.TeacherDto;
import com.korki.common.models.enums.LessonLocation;
import com.korki.common.models.enums.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class AdvertDto {

    private Long id;
    private Subject subject;
    private String description;
    private Set<LessonLocation> locations = new HashSet<>();
    private float price;
    private TeacherDto teacherDto;
}
