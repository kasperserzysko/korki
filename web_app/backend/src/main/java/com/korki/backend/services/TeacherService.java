package com.korki.backend.services;

import com.korki.backend.dtos.AdvertCreateDto;
import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.TeacherDetailsDto;
import com.korki.backend.services.interfaces.ITeacherService;
import com.korki.backend.utills.Mapper;
import com.korki.common.models.Advert;
import com.korki.common.repositories.AdvertRepository;
import com.korki.common.repositories.TeacherRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final AdvertRepository advertRepository;

    private final Validator validator;
    private final Mapper mapper;
    private final String FOLDER_PATH = "D:/kasperserzysko_repository/korki/teacher_photos/";

    @Override
    public void addAdvert(AdvertCreateDto dto, SecurityUser loggedUser) {
        var teacherEntity = loggedUser.getUser().getTeacher();
        var advertEntity = new Advert();
        validator.validate(dto);

        mapper.mapEntity(advertEntity, dto);
        teacherEntity.getAdverts().add(advertEntity);
        advertEntity.setTeacher(teacherEntity);
        advertRepository.save(advertEntity);
        teacherRepository.save(teacherEntity);
    }

    @Override
    public void updateTeacher(TeacherDetailsDto dto, SecurityUser loggedUser) throws IOException {
        var teacherEntity = loggedUser.getUser().getTeacher();

        mapper.mapEntity(teacherEntity, dto);

        saveImage(dto.getImage(), teacherEntity.getId());

        teacherRepository.save(teacherEntity);
    }
    

    private void saveImage(MultipartFile file, Long teacherId) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(FOLDER_PATH + teacherId + ".jpg");
        Files.write(path, bytes);

    }
}
