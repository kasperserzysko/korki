package com.korki.backend.utills;

import com.korki.backend.annotations.DtoFor;
import com.korki.backend.annotations.MappingField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.NoSuchElementException;

@Component
public class Mapper {

    public <E, D> void mapToEntity(E entity, D dto) {
        map(entity, dto, true);
    }
    public <E, D> void mapToDto(E entity, D dto) {
        map(entity, dto, false);
    }

    public <E, D> void map(E entity, D dto, boolean isEntityMapping) {
        checkClass(entity, dto);

        Field[] dtoFields = Arrays.stream(dto.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(MappingField.class) != null)
                .toArray(Field[]::new);

        for (Field dtoField : dtoFields) {
            dtoField.setAccessible(true);
            Field entityField = null;
            try {
                entityField = entity.getClass().getDeclaredField(dtoField.getAnnotation(MappingField.class).fieldName());
                entityField.setAccessible(true);
                if (isEntityMapping) {
                    setField(entityField, dtoField, entity, dto);
                }else {
                    setField(dtoField, entityField, dto, entity);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private <E, D> void checkClass( E entity, D dto) {
        if (dto.getClass().getAnnotation(DtoFor.class).classType() != entity.getClass()) {
            throw new NoSuchElementException(MessageFormat
                    .format("Entity Class: {0} is different than DTO target class: {1}", entity.getClass().getName(), dto.getClass().getAnnotation(DtoFor.class).classType().getName()));
        }
    }
    private <T, S> void setField(Field targetField, Field sourceField, T targetClass, S sourceClass ) throws IllegalAccessException {
        targetField.set(targetClass, sourceField.get(sourceClass));
    }
}
