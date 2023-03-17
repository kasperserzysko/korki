package com.korki.backend.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder({
        "name",
        "latitude",
        "longitude",
        "country",
        "population",
        "is_capital"
})
public class City {

    @JsonProperty("name")
    private String name;
}
