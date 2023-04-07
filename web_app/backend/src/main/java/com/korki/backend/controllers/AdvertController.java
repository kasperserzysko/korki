package com.korki.backend.controllers;

import com.korki.backend.dtos.advert_dtos.AdvertDetailsDto;
import com.korki.backend.dtos.advert_dtos.AdvertDto;
import com.korki.backend.exceptions.NotFoundException;
import com.korki.backend.services.interfaces.IAdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adverts")
public class AdvertController {

    private final IAdvertService advertService;

    @GetMapping("/{city}")
    public ResponseEntity<List<AdvertDto>> getAdverts(@RequestParam("price") Optional<Float> price,
                                                      @RequestParam("free") Optional<Boolean> free,
                                                      @RequestParam("scope") Optional<List<String>> scopes,
                                                      @RequestParam("location") Optional<List<String>> locations,
                                                      @RequestParam("weekdays") Optional<List<String>> weekdays,
                                                      @RequestParam("education") Optional<Integer> education,
                                                      @RequestParam("experience") Optional<Integer> experience,
                                                      @RequestParam("seniority") Optional<Integer> seniority,
                                                      @RequestParam("age_min") Optional<Integer> ageMin,
                                                      @RequestParam("age_max") Optional<Integer> ageMax,
                                                      @RequestParam("gender") Optional<String> gender,
                                                      @PathVariable("city") String city,
                                                      @RequestParam("page") Optional<Integer> page){

        return ResponseEntity.ok(advertService.getCityAdverts(price, free, scopes, locations, weekdays, education, experience, seniority, ageMin, ageMax, gender, page, city));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertDetailsDto> getAdvert(@PathVariable("id") Long advertId) throws NotFoundException {
        return ResponseEntity.ok(advertService.getAdvert(advertId));
    }



    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException ex){
        return ex.getMessage();
    }
}
