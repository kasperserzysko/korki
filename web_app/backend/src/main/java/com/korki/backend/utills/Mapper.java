package com.korki.backend.utills;

import com.korki.backend.annotations.DtoFor;
import com.korki.backend.annotations.MappingField;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.NoSuchElementException;

@Component
public class Mapper {

    public <E, D> void mapEntity(E entity, D dto) {
        checkClass(entity, dto);

        Field[] dtoFields = Arrays.stream(dto.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(MappingField.class) != null)
                .toArray(Field[]::new);

        for (Field field : dtoFields) {
            field.setAccessible(true);
            Field entityField = null;
            try {
                entityField = entity.getClass().getDeclaredField(field.getAnnotation(MappingField.class).fieldName());
                entityField.setAccessible(true);
                entityField.set(entity, field.get(dto));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private <E, D> void checkClass(E entity, D dto) {
        if (dto.getClass().getAnnotation(DtoFor.class).classType() != entity.getClass()) {
            throw new NoSuchElementException(MessageFormat
                    .format("Entity Class: {0} is different than DTO target class: {1}", entity.getClass().getName(), dto.getClass().getAnnotation(DtoFor.class).classType().getName()));
        }
    }
}
