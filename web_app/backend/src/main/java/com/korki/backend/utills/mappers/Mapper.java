package com.korki.backend.utills.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Mapper implements IMapper{

    private final AdvertMapper advertMapper;
    private final UserMapper userMapper;
    private final TeacherMapper teacherMapper;
    private final RatingMapper ratingMapper;

    @Override
    public AdvertMapper getAdvertMapper() {
        return advertMapper;
    }

    @Override
    public UserMapper getUserMapper() {
        return userMapper;
    }

    @Override
    public TeacherMapper getTeacherMapper() {
        return teacherMapper;
    }

    @Override
    public RatingMapper getRatingMapper() {
        return ratingMapper;
    }


}
