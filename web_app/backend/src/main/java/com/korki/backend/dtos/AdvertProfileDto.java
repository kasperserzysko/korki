package com.korki.backend.dtos;

import com.korki.common.models.enums.LessonLocation;
import com.korki.common.models.enums.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class AdvertProfileDto {
    private Long id;
    private Subject subject;
    private float price;
    private Set<LessonLocation> lessonLocation;
    private String city;
}
