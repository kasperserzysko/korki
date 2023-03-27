package com.korki.common.models;

import com.korki.common.models.enums.Education;
import com.korki.common.models.enums.Experience;
import com.korki.common.models.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //CREDENTIALS
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birthday;
    private int age;
    private Education education;
    private Experience experience;
    private Year yearOfCareerStart = Year.now();
    private int seniority = 0;

    //ADDRESS AND CONTACTS
    private String voivodeship;
    private String city;
    private String district;
    private String phoneNumber;



    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "teacher")
    private Set<TeacherRating> ratings = new HashSet<>();

    @OneToMany(mappedBy = "teacher")
    private Set<Advert> adverts = new HashSet<>();
}
