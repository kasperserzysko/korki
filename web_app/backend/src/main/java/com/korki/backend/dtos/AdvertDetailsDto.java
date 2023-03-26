package com.korki.backend.dtos;

import com.korki.backend.annotations.DtoFor;
import com.korki.backend.annotations.MappingField;
import com.korki.common.models.Advert;
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
@DtoFor(classType = Advert.class)
public class AdvertDetailsDto {

    @NotNull
    @MappingField(fieldName = "subject")
    private Subject subject;

    @NotNull
    @NotBlank
    @MappingField(fieldName = "description")
    private String description;

    @NotNull
    @Positive
    @MappingField(fieldName = "price")
    private float price;

    @NotNull
    @MappingField(fieldName = "freeLesson")
    private boolean freeLesson;

    @NotNull
    @MappingField(fieldName = "lessonLocation")
    private LessonLocation lessonLocation;

    @NotNull
    @MappingField(fieldName = "weekdays")
    private Set<Weekday> weekdays = new HashSet<>();

    @NotNull
    @MappingField(fieldName = "teachingScopes")
    private Set<TeachingScope> teachingScopes = new HashSet<>();
}
