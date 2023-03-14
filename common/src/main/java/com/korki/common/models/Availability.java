package com.korki.common.models;

import com.korki.common.models.enums.Time;
import com.korki.common.models.enums.Weekday;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;
    @Enumerated(EnumType.STRING)
    private Time time;

    @ManyToOne
    private Teacher teacher;
}
