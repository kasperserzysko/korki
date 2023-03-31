package com.korki.backend.dtos.advert_dtos;

import com.korki.backend.dtos.teacher_dtos.TeacherDto;
import com.korki.common.models.enums.LessonLocation;
import com.korki.common.models.enums.Subject;
import com.korki.common.models.enums.TeachingScope;
import com.korki.common.models.enums.Weekday;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class AdvertDetailsDto {
    private Subject subject;
    private String description;
    private float price;
    private boolean freeLesson;
    private Set<LessonLocation> lessonLocation = new HashSet<>();
    private Set<Weekday> weekdays = new HashSet<>();
    private Set<TeachingScope> teachingScopes = new HashSet<>();
    private TeacherDto teacher;
}
