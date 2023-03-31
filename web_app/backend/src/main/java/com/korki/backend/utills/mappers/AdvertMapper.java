package com.korki.backend.utills.mappers;

import com.korki.backend.dtos.advert_dtos.AdvertCreateDto;
import com.korki.backend.dtos.advert_dtos.AdvertDetailsDto;
import com.korki.backend.dtos.advert_dtos.AdvertDisplayDto;
import com.korki.backend.dtos.advert_dtos.AdvertDto;
import com.korki.backend.dtos.teacher_dtos.TeacherDto;
import com.korki.backend.utills.Validator;
import com.korki.common.models.Advert;
import com.korki.common.models.Rating;
import com.korki.common.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class AdvertMapper {

    @Autowired
    private Validator validator;

    public final BiConsumer<AdvertCreateDto, Advert> mapToEntity = (dto, advert) -> {
        validator.validate(dto);

        advert.setSubject(dto.getSubject());
        advert.setDescription(dto.getDescription());
        advert.setPrice(dto.getPrice());
        advert.setFreeLesson(dto.isFreeLesson());
        advert.setLessonLocation(dto.getLessonLocation());
        advert.setWeekdays(dto.getWeekdays());
        advert.setTeachingScopes(dto.getTeachingScopes());
    };
    public final Function<Advert, AdvertCreateDto> mapToAdvertCreate = advert -> {
        var dto = new AdvertCreateDto();
        dto.setSubject(advert.getSubject());
        dto.setDescription(advert.getDescription());
        dto.setPrice(advert.getPrice());
        dto.setFreeLesson(dto.isFreeLesson());
        dto.setLessonLocation(advert.getLessonLocation());
        dto.setWeekdays(advert.getWeekdays());
        dto.setTeachingScopes(advert.getTeachingScopes());
        return dto;
    };
    public final BiFunction<Advert, Teacher, AdvertDisplayDto> mapToAdvertDisplay = (advert, teacher) -> {
        var dto = new AdvertDisplayDto();
        dto.setId(advert.getId());
        dto.setSubject(advert.getSubject());
        dto.setPrice(advert.getPrice());
        dto.setCity(teacher.getCity());
        return dto;
    };

    public final Function<Advert, AdvertDto> mapToAdvertDto = advert -> {
      var dto = new AdvertDto();
      dto.setId(advert.getId());
      dto.setSubject(advert.getSubject());
      dto.setDescription(advert.getDescription());
      dto.setLocations(advert.getLessonLocation());
      dto.setPrice(advert.getPrice());

      var teacherRatingDto = new TeacherDto();
      teacherRatingDto.setFirstName(advert.getTeacher().getFirstName());
      teacherRatingDto.setRating(countRating(advert));
      dto.setTeacherDto(teacherRatingDto);
      return dto;
    };

    public final Function<Advert, AdvertDetailsDto> mapToAdvertDetails = advert -> {
        var dto = new AdvertDetailsDto();
        dto.setSubject(advert.getSubject());
        dto.setDescription(advert.getDescription());
        dto.setPrice(advert.getPrice());
        dto.setFreeLesson(dto.isFreeLesson());
        dto.setLessonLocation(advert.getLessonLocation());
        dto.setWeekdays(advert.getWeekdays());
        dto.setTeachingScopes(advert.getTeachingScopes());
        
        var teacher = new TeacherDto();
        teacher.setId(advert.getTeacher().getId());
        teacher.setFirstName(advert.getTeacher().getFirstName());
        dto.setTeacher(teacher);
        return dto;
    };


    private float countRating(Advert advert){
        var ratingSet = advert.getTeacher().getRatings();
        var ratingAmount = ratingSet.size();
        var ratingCount = ratingSet.stream().mapToInt(Rating::getRating).sum();
        return (float) Math.round(((float) (ratingCount / ratingAmount)) * 100) / 100;
    }
}
