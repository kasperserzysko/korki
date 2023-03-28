package com.korki.backend.services;

import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.services.interfaces.IStudentService;
import com.korki.common.repositories.AdvertRepository;
import com.korki.common.repositories.StudentRepository;
import com.korki.common.repositories.TeacherRepository;
import com.korki.email_service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final AdvertRepository advertRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    private final EmailService emailService;

    @Override
    public void applyForAdvert(Long advertId, SecurityUser loggedUser) {
        var advertEntity = advertRepository.findById(advertId)
                .orElseThrow();     //TODO
        var studentEntity = loggedUser.getUser().getStudent();
        var teacherEntity = teacherRepository.getTeacherByAdvertId(advertId);

        advertEntity.getStudentsApplied().add(studentEntity);
        studentEntity.getAdvertsApplied().add(advertEntity);
        emailService.sendApplyAdvertMessage(teacherEntity.getUser().getEmail(),
                studentEntity.getFirstName(),
                teacherEntity.getFirstName());

        studentRepository.save(studentEntity);
    }

}
