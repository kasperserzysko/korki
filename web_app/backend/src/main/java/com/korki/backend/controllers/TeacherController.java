package com.korki.backend.controllers;

import com.korki.backend.dtos.rating_dtos.RatingDto;
import com.korki.backend.exceptions.NotFoundException;
import com.korki.backend.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class TeacherController {

    private final RatingService ratingService;

    @GetMapping("/{id}/ratings")
    public ResponseEntity<List<RatingDto>> getRatings(@PathVariable("id") Long teacherId, @RequestParam(name = "page", required = false)Optional<Integer> page) throws NotFoundException {
        return ResponseEntity.ok(ratingService.getRatings(teacherId, page));
    }















    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException ex){
        return ex.getMessage();
    }
}
