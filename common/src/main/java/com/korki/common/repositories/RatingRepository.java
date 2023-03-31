package com.korki.common.repositories;

import com.korki.common.models.Rating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> getAllByTeacherId(Long teacherId, Pageable pageable);
}
