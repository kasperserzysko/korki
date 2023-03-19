package com.korki.common.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pictureURL;
    private String city;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "teacher")
    private Set<TeacherRating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "teacher")
    private Set<Advert> adverts = new HashSet<>();

    @OneToMany(mappedBy = "teacher")
    private Set<Availability> availabilities = new HashSet<>();
}
