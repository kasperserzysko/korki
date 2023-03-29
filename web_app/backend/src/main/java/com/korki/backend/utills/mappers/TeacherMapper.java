package com.korki.backend.utills.mappers;

import com.korki.backend.dtos.teacher_dtos.TeacherCredentialsDto;
import com.korki.backend.dtos.teacher_dtos.TeacherDisplayDto;
import com.korki.backend.utills.Validator;
import com.korki.common.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;
import java.util.function.Function;

@Component
public class TeacherMapper {

    @Autowired
    private Validator validator;
    
    public final BiConsumer<TeacherCredentialsDto, Teacher> mapToEntity = (dto, teacher) -> {
        validator.validate(dto);
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setGender(dto.getGender());
        teacher.setBirthday(dto.getBirthday());
        teacher.setEducation(dto.getEducation());
        teacher.setExperience(dto.getExperience());
        teacher.setYearOfCareerStart(dto.getYearOfCareerStart());
        teacher.setVoivodeship(dto.getVoivodeship());
        teacher.setCity(dto.getCity());
        teacher.setDistrict(dto.getDistrict());
        teacher.setPhoneNumber(dto.getPhoneNumber());
    };
    public final Function<Teacher, TeacherDisplayDto> mapToDisplayDto = teacher -> {
        var dto = new TeacherDisplayDto();
        dto.setFirstName(teacher.getFirstName());
        dto.setGender(teacher.getGender());
        return dto;
    };
    public final Function<Teacher, TeacherCredentialsDto> mapToCredentialsDto = teacher -> {
        var dto = new TeacherCredentialsDto();
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setGender(teacher.getGender());
        dto.setBirthday(teacher.getBirthday());
        dto.setEducation(teacher.getEducation());
        dto.setExperience(teacher.getExperience());
        dto.setYearOfCareerStart(teacher.getYearOfCareerStart());
        dto.setVoivodeship(teacher.getVoivodeship());
        dto.setCity(teacher.getCity());
        dto.setDistrict(teacher.getDistrict());
        dto.setPhoneNumber(teacher.getPhoneNumber());
        return dto;
    };
}
