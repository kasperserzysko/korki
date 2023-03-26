package com.korki.backend.services;

import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.TeacherCredentialsDto;
import com.korki.backend.services.interfaces.ITeacherService;
import com.korki.backend.utills.Mapper;
import com.korki.backend.utills.Validator;
import com.korki.common.models.User;
import com.korki.common.repositories.AdvertRepository;
import com.korki.common.repositories.TeacherRepository;
import com.korki.common.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService{

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final AdvertRepository advertRepository;

    private final Mapper mapper;
    private final Validator validator;

    private final String FOLDER_PATH = "D:/kasperserzysko_repository/korki/teacher_photos/";




    @Override
    public void updateProfile(TeacherCredentialsDto dto, SecurityUser loggedUser) throws Exception {
        validator.validate(dto);

        var userEntity = loggedUser.getUser();
        var teacherEntity = userEntity.getTeacher();

        mapper.mapToEntity(teacherEntity, dto);
        saveAccountCredentials(dto.getEmail(), dto.getPassword(), userEntity);
        saveImage(dto.getImage(), teacherEntity.getId());

        teacherRepository.save(teacherEntity);
        userRepository.save(userEntity);
    }

    @Override
    public TeacherCredentialsDto getTeacherCredentials(SecurityUser loggedUser) {
        var teacherEntity = loggedUser.getUser().getTeacher();
        var teacherDto = new TeacherCredentialsDto();

        mapper.mapToDto(teacherEntity, teacherDto);
        return teacherDto;
    }



    private void saveImage(MultipartFile file, Long teacherId) throws IOException {
        if (file != null) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FOLDER_PATH + teacherId + ".jpg");
            Files.write(path, bytes);
        }
    }
    private void saveAccountCredentials(String email, String password, User user) throws Exception {
        if (email != null) {
            validateEmail(email, user.getId());
            user.setEmail(email);
        }
        if (password != null){
            user.setPassword(password);
        }
    }

    private void validateEmail(String email, Long userId) throws Exception {
        if (userRepository.existsByEmail(email, userId)){
            throw new Exception("Email already exist!");
        }
    }
}