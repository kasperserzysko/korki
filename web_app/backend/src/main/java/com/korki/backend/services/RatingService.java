package com.korki.backend.services;

import com.korki.backend.dtos.rating_dtos.RatingDto;
import com.korki.backend.exceptions.NotFoundException;
import com.korki.backend.services.interfaces.IRatingService;
import com.korki.backend.utills.mappers.IMapper;
import com.korki.common.repositories.RatingRepository;
import com.korki.common.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService implements IRatingService {

    private final RatingRepository ratingRepository;
    private final TeacherRepository teacherRepository;

    private final IMapper mapper;
    @Override
    public List<RatingDto> getRatings(Long teacherId, Optional<Integer> page) throws NotFoundException {
        final int ITEMS_PER_PAGE = 10;
        final Pageable ratingPage;
        teacherRepository
                .findById(teacherId)
                .orElseThrow(() -> new NotFoundException("Couldn't find teacher with id: " + teacherId));

        ratingPage = page.map(p -> PageRequest.of(p, ITEMS_PER_PAGE))
                .orElseGet(() ->  PageRequest.of(0, ITEMS_PER_PAGE));

        return ratingRepository
                .getAllByTeacherId(teacherId, ratingPage)
                .stream()
                .map(mapper.getRatingMapper().mapToRatingDto)
                .toList();
    }
}
