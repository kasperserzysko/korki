package com.korki.common.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "teacher")
    private List<Advert> adverts = new ArrayList<>();
}
