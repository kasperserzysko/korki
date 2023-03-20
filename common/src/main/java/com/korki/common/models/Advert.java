package com.korki.common.models;

import com.korki.common.models.enums.LessonLocation;
import com.korki.common.models.enums.Subject;
import com.korki.common.models.enums.TeachingScope;
import com.korki.common.models.enums.Weekday;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Subject subject;
    private String description;
    private float price;
    private boolean freeLesson;


    @Enumerated(EnumType.STRING)
    private LessonLocation lessonLocation;

    @ElementCollection(targetClass = Weekday.class)
    @JoinTable(name = "advert_weekdays", joinColumns = @JoinColumn(name = "advert_id"))
    @Column(name = "weekday", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Weekday> weekdays = new HashSet<>();

    @ElementCollection(targetClass = TeachingScope.class)
    @JoinTable(name = "advert_scopes", joinColumns = @JoinColumn(name = "advert_id"))
    @Column(name = "scope", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<TeachingScope> teachingScopes = new HashSet<>();

    @ManyToOne
    private Teacher teacher;

    @ManyToMany
    @JoinTable(
            name = "adverts_students_applied",
            joinColumns = @JoinColumn(name = "advert_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> studentsApplied = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "adverts_students_accepted",
            joinColumns = @JoinColumn(name = "advert_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> studentsAccepted = new HashSet<>();

}
