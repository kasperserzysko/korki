package com.korki.backend.dtos;

import com.korki.backend.annotations.DtoFor;
import com.korki.backend.annotations.MappingField;
import com.korki.common.models.Advert;
import com.korki.common.models.enums.Subject;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DtoFor(classType = Advert.class)
public class AdvertCreateDto {

    @Min(value = 0)
    @MappingField(fieldName = "price")
    private float price;

    @NotNull
    @MappingField(fieldName = "subject")
    private Subject subject;

    @MappingField(fieldName = "online")
    private boolean online;

    @MappingField(fieldName = "city")
    private String city;
}
