package com.korki.common.repositories;

import com.korki.common.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher t LEFT JOIN FETCH t.adverts WHERE t.id = :teacherId")
    Teacher getTeacherWithAdverts(@Param("teacherId") Long teacherId);

    @Query("SELECT t FROM Teacher t JOIN t.adverts a WHERE a.id = :advertId")
    Teacher getTeacherByAdvertId(Long advertId);
}
