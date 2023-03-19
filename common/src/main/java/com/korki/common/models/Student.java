package com.korki.common.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToMany(mappedBy = "studentsApplied")
    private Set<Advert> advertsApplied = new HashSet<>();
    @ManyToMany(mappedBy = "studentsAccepted")
    private Set<Advert> advertsAccepted = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<TeacherRating> ratings = new HashSet<>();
}
