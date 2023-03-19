package com.korki.backend.dtos;

import com.korki.backend.annotations.DtoFor;
import com.korki.backend.annotations.MappingField;
import com.korki.common.models.Teacher;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@DtoFor(classType = Teacher.class)
public class TeacherDetailsDto {

    private MultipartFile image;
    @MappingField(fieldName = "description")
    private String description;
}
