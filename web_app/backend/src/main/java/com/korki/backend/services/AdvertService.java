package com.korki.backend.services;

import com.korki.backend.dtos.AdvertDetailsDto;
import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.services.interfaces.IAdvertService;
import com.korki.backend.utills.Mapper;
import com.korki.backend.utills.Validator;
import com.korki.common.models.Advert;
import com.korki.common.repositories.AdvertRepository;
import com.korki.common.repositories.TeacherRepository;
import com.korki.common.repositories.specifications.AdvertSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertService implements IAdvertService {

    private final Validator validator;
    private final Mapper mapper;

    private final TeacherRepository teacherRepository;
    private final AdvertRepository advertRepository;

    @Override
    public void createAdvert(AdvertDetailsDto dto, SecurityUser loggedUser) {
        validator.validate(dto);
        var teacherEntityWithAdverts = teacherRepository.getTeacherWithAdverts(loggedUser.getUser().getTeacher().getId());
        var advertEntity = new Advert();

        mapper.mapToEntity(advertEntity, dto);

        teacherEntityWithAdverts.getAdverts().add(advertEntity);
        advertEntity.setTeacher(teacherEntityWithAdverts);

        advertRepository.save(advertEntity);
        teacherRepository.save(teacherEntityWithAdverts);
    }

    public void getAllAdverts(Optional<Float> price,
                              Optional<Boolean> free,
                              Optional<List<String>> scopes,
                              Optional<List<String>> locations,     //TODO
                              Optional<List<String>> weekdays,
                              Optional<Integer> education,
                              Optional<Integer> experience,
                              Optional<Integer> seniority,
                              Optional<Integer> ageMin,
                              Optional<Integer> ageMax,
                              Optional<String> gender,
                              String city){

        List<Specification<Advert>> specifications = new ArrayList<>();
        price.ifPresent(p -> specifications
                .add(AdvertSpecification.priceLessOrEqualsThan(p)));
        free.ifPresent(f -> specifications
                .add(AdvertSpecification.hasFreeLesson()));
        scopes.ifPresent(s -> specifications
                .add(AdvertSpecification.isInTeachingScope(s)));
        locations.ifPresent(l -> specifications
                .add(AdvertSpecification.isInLocation(l)));
        weekdays.ifPresent(w -> specifications
                .add(AdvertSpecification.isInWeekdays(w)));
        education.ifPresent(e -> specifications
                .add(AdvertSpecification.hasMinEducation(e)));
        experience.ifPresent(e -> specifications
                .add(AdvertSpecification.hasMinExperience(e)));
        seniority.ifPresent(s -> specifications
                .add(AdvertSpecification.hasMinSeniority(s)));
        ageMin.ifPresent(a -> specifications
                .add(AdvertSpecification.olderThan(a)));
        ageMax.ifPresent(a -> specifications
                .add(AdvertSpecification.youngerThan(a)));
        gender.ifPresent(g -> specifications
                .add(AdvertSpecification.gender(g)));

        advertRepository.getAdvertsByTeacherCity(city, Specification.allOf(specifications));

    }
}
