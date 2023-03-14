package com.korki.common.repositories;

import com.korki.common.models.TeacherRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRatingRepository extends JpaRepository<TeacherRating, Long> {
}
