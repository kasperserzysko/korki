package com.korki.backend.services;

import com.korki.backend.dtos.AdvertDetailsDto;
import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.services.interfaces.IAdvertService;
import com.korki.backend.utills.Mapper;
import com.korki.backend.utills.Validator;
import com.korki.common.models.Advert;
import com.korki.common.repositories.AdvertRepository;
import com.korki.common.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void getAll(){

        advertRepository.findAll();
    }
}
