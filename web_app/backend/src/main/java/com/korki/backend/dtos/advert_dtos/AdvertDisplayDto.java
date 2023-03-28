package com.korki.backend.dtos.advert_dtos;

import com.korki.common.models.enums.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvertDisplayDto {

    private Long id;
    private Subject subject;
    private String city;
    private float price;
}
