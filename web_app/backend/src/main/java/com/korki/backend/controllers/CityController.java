package com.korki.backend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.korki.backend.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping()
    public ResponseEntity<List<String>> getCities(@RequestParam("name") String name){
        try {
            return ResponseEntity.ok(cityService.getCities(name));
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
