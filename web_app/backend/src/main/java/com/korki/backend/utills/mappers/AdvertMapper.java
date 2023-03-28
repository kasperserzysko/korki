package com.korki.backend.utills.mappers;

import com.korki.backend.dtos.AdvertDetailsDto;
import com.korki.backend.dtos.AdvertProfileDto;
import com.korki.backend.dtos.advert_dtos.AdvertDisplayDto;
import com.korki.backend.utills.Validator;
import com.korki.common.models.Advert;
import com.korki.common.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AdvertMapper {

    private final Validator validator;

    public final BiConsumer<AdvertDetailsDto, Advert> mapToEntity = (dto, advert) -> {
        validator.validate(dto);

        advert.setSubject(dto.getSubject());
        advert.setDescription(dto.getDescription());
        advert.setPrice(dto.getPrice());
        advert.setFreeLesson(dto.isFreeLesson());
        advert.setLessonLocation(dto.getLessonLocation());
        advert.setWeekdays(dto.getWeekdays());
        advert.setTeachingScopes(dto.getTeachingScopes());
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
    public final Function<Advert, AdvertProfileDto> mapToAdvertProfile = advert -> {
        var advertDto = new AdvertProfileDto();
        advertDto.setId(advert.getId());
        advertDto.setSubject(advert.getSubject());
        advertDto.setPrice(advert.getPrice());
        advertDto.setLessonLocation(advert.getLessonLocation());
        advertDto.setCity(advert.getTeacher().getCity());
        return advertDto;
    };

}
