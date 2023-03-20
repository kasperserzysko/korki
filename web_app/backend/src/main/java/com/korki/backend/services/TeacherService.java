package com.korki.backend.services;

import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.services.interfaces.ITeacherService;
import com.korki.backend.utills.Mapper;
import com.korki.common.repositories.AdvertRepository;
import com.korki.common.repositories.AvailabilityRepository;
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
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AdvertRepository advertRepository;
    private final AvailabilityRepository availabilityRepository;

    private final Validator validator;
    private final Mapper mapper;

    private final String FOLDER_PATH = "D:/kasperserzysko_repository/korki/teacher_photos/";



    private void saveImage(MultipartFile file, Long teacherId) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(FOLDER_PATH + teacherId + ".jpg");
        Files.write(path, bytes);

    }
}
