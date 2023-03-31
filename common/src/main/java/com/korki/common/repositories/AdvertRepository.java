package com.korki.common.repositories;

import com.korki.common.models.Advert;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long>, JpaSpecificationExecutor<Advert> {

    @Query("SELECT a FROM Advert a JOIN FETCH Teacher t ON a.teacher = t JOIN FETCH t.ratings WHERE t.city = :city")
    List<Advert> getAdvertsByCity(@Param("city") String city, Pageable pageable);

    @Query("SELECT a FROM Advert a JOIN Teacher t ON a.teacher = t WHERE t.id = :teacherId")
    List<Advert> getAdvertsByTeacherId(Long teacherId);
}
