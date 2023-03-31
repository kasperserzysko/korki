package com.korki.backend.dtos.advert_dtos;

import com.korki.common.models.enums.LessonLocation;
import com.korki.common.models.enums.Subject;
import com.korki.common.models.enums.TeachingScope;
import com.korki.common.models.enums.Weekday;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class AdvertCreateDto {

    @NotNull
    private Subject subject;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Positive
    private float price;

    @NotNull
    private boolean freeLesson;

    @NotNull
    private Set<LessonLocation> lessonLocation = new HashSet<>();

    @NotNull
    private Set<Weekday> weekdays = new HashSet<>();

    @NotNull
    private Set<TeachingScope> teachingScopes = new HashSet<>();
}
