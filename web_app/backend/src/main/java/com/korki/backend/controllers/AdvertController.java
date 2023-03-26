package com.korki.backend.controllers;

import com.korki.backend.dtos.AdvertDetailsDto;
import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.services.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adverts")
public class AdvertController {

    private final AdvertService advertService;

    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    @PostMapping
    public ResponseEntity<?> createAdvert(@RequestBody AdvertDetailsDto dto, @AuthenticationPrincipal SecurityUser user){
        advertService.createAdvert(dto, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{city}")
    public void getAdverts(@RequestParam("price") Optional<Float> price,
                           @RequestParam("free") Optional<Boolean> free,
                           @RequestParam("scope") Optional<List<String>> scopes,
                           @RequestParam("location") Optional<List<String>> locations,
                           @RequestParam("weekdays") Optional<List<String>> weekdays,
                           @RequestParam("education") Optional<String> education,
                           @RequestParam("experience") Optional<String> experience,
                           @RequestParam("seniority") Optional<String> seniority,
                           @RequestParam("age_min") Optional<Integer> ageMin,
                           @RequestParam("age_max") Optional<Integer> ageMax,
                           @RequestParam("gender") Optional<String> gender,
                           @PathVariable("city") String city){

    }
}
