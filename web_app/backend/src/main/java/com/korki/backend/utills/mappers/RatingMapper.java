package com.korki.backend.utills.mappers;

import com.korki.backend.dtos.rating_dtos.RatingDto;
import com.korki.backend.dtos.user_dtos.UserDto;
import com.korki.common.models.Rating;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RatingMapper {

    public final Function<Rating, RatingDto> mapToRatingDto = rating -> {
        var dto = new RatingDto();
        dto.setRating(rating.getRating());
        dto.setComment(rating.getComment());
        dto.setDate(rating.getDate());

        var user = new UserDto();
        if (rating.getTeacher() == null){
            user.setId(rating.getStudent().getId());
            user.setFirstName(rating.getStudent().getFirstName());
        }else {
            user.setId(rating.getTeacher().getId());
            user.setFirstName(rating.getTeacher().getFirstName());
        }
        dto.setUser(user);
        return dto;
    };
}
