package com.korki.common.models;

import com.korki.common.models.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String phoneNumber;

    @Column(name = "activation_link")
    private String activationLink = UUID.randomUUID().toString();

    private boolean isEnabled = false;               //TODO USTAWIC NA FALSE

    @OneToOne(mappedBy = "user")
    private Teacher teacher;

    @OneToOne(mappedBy = "user")
    private Student student;

    @Enumerated(EnumType.STRING)
    private Role role;

}
