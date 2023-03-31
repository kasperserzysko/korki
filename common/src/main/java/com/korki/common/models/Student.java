package com.korki.common.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToMany(mappedBy = "studentsApplied")
    private Set<Advert> advertsApplied = new HashSet<>();
    @ManyToMany(mappedBy = "studentsAccepted")
    private Set<Advert> advertsAccepted = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Rating> ratings = new HashSet<>();
}
