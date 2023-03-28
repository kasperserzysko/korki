package com.korki.backend.services;

import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.teacher_dtos.TeacherCredentialsDto;
import com.korki.backend.dtos.teacher_dtos.TeacherDisplayDto;
import com.korki.backend.exceptions.EmailFoundException;
import com.korki.backend.services.interfaces.ITeacherService;
import com.korki.backend.utills.FileService;
import com.korki.backend.utills.mappers.IMapper;
import com.korki.common.models.User;
import com.korki.common.repositories.TeacherRepository;
import com.korki.common.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService implements ITeacherService{

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    private final IMapper mapper;


    @Override
    public TeacherDisplayDto getTeacherProfileDisplay(SecurityUser loggedUser) {
        return mapper.getTeacherMapper()
                .mapToDisplayDto
                .apply(loggedUser.getUser().getTeacher());
    }

    @Override
    public void updateProfile(TeacherCredentialsDto dto, MultipartFile file, SecurityUser loggedUser) throws EmailFoundException, IOException {

        var userEntity = loggedUser.getUser();
        var teacherEntity = userEntity.getTeacher();

        mapper.getTeacherMapper()
                .mapToEntity
                .accept(dto, teacherEntity);

        saveAccountCredentials(dto.getEmail(), dto.getPassword(), userEntity);
        FileService.saveImage(file, teacherEntity.getId());

        teacherRepository.save(teacherEntity);
        userRepository.save(userEntity);
    }

    @Override
    public TeacherCredentialsDto getTeacherCredentials(SecurityUser loggedUser) {
        return mapper.getTeacherMapper()
                .mapToCredentialsDto
                .apply(loggedUser.getUser().getTeacher());
    }




    private void saveAccountCredentials(String email, String password, User user) throws EmailFoundException {
        if (email != null) {
            validateEmail(email, user.getId());
            user.setEmail(email);
        }
        if (password != null){
            user.setPassword(password);
        }
    }

    private void validateEmail(String email, Long userId) throws EmailFoundException {
        if (userRepository.existsByEmail(email, userId)){
            throw new EmailFoundException("Email already exist!");
        }
    }
}
