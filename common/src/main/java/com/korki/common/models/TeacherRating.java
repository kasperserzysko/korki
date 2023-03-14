package com.korki.common.models;

import com.korki.common.models.composite_keys.TeacherRatingKey;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TeacherRating {

    @EmbeddedId
    private TeacherRatingKey id;

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("teacher_id")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private int rating;
}
