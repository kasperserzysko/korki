package com.korki.common.models;

import com.korki.common.models.enums.Subject;
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
    private float price;
    @ManyToOne
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Subject subject;

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
