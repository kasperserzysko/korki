package com.korki.backend.services.interfaces;

import com.korki.backend.dtos.rating_dtos.RatingDto;
import com.korki.backend.exceptions.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IRatingService {

    List<RatingDto> getRatings(Long teacherId, Optional<Integer> page) throws NotFoundException;
}
