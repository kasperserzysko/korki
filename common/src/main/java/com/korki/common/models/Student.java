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

    @ManyToOne
    private User user;
    @ManyToMany(mappedBy = "studentsApplied")
    private Set<Advert> advertsApplied = new HashSet<>();
    @ManyToMany(mappedBy = "studentsAccepted")
    private Set<Advert> advertsAccepted = new HashSet<>();
}
