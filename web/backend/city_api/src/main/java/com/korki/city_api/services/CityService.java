package com.korki.city_api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.korki.city_api.daos.City;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;


@Service
public class CityService{

    private final static String API_KEY = "Y1r5eOAyKAoOcwBRQvMN9w==amsAhWzMwRMq1WNs";
    private final static String API_URL = "https://api.api-ninjas.com/v1/city?country=PL&limit=5&name=";

    public List<String> getCities(String city) throws JsonProcessingException {
        var url = API_URL + city;
        WebClient client = WebClient.create();

        WebClient.ResponseSpec responseSpec = client.get()
                .uri(url)
                .header("accept", "application/json")
                .header("x-api-key", API_KEY)
                .retrieve();
        String response = responseSpec.bodyToMono(String.class).block();
        Gson gson = new Gson();
        var cities = gson.fromJson(response, City[].class);

        return Arrays.stream(cities).map(City::getName).toList();
    }
}
