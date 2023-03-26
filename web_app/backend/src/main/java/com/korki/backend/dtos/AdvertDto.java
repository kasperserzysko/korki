package com.korki.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.korki.common.models.enums.LessonLocation;
import com.korki.common.models.enums.Subject;
import com.korki.common.models.enums.TeachingScope;
import com.korki.common.models.enums.Weekday;

import java.util.Set;

public class AdvertDto {

    private Long id;
    private Subject subject;
    private String description;
    private float price;
    private boolean freeLesson;
    private LessonLocation lessonLocation;
    private Set<Weekday> weekdays;
    private Set<TeachingScope> teachingScopes;

    @JsonProperty("teacher")
    private TeacherDto teacherDto;

}
