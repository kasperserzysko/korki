package com.korki.backend.services.interfaces;


import com.korki.backend.dtos.advert_dtos.AdvertCreateDto;
import com.korki.backend.dtos.user_dtos.SecurityUser;
import com.korki.backend.dtos.advert_dtos.AdvertDetailsDto;
import com.korki.backend.dtos.advert_dtos.AdvertDisplayDto;
import com.korki.backend.dtos.advert_dtos.AdvertDto;
import com.korki.backend.exceptions.NoAccessException;
import com.korki.backend.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;


public interface IAdvertService {

    void createAdvert(AdvertCreateDto dto, SecurityUser loggedUser);
    List<AdvertDisplayDto> getProfileAdverts(SecurityUser securityUser);
    void updateAdvert(AdvertCreateDto dto, Long advertId) throws NotFoundException;
    AdvertCreateDto getAdvertEditDetails(Long advertId) throws NotFoundException;
    void deleteAdvert(Long advertId, SecurityUser loggedUser) throws NotFoundException, NoAccessException;
    List<AdvertDto> getCityAdverts(Optional<Float> price,
                                   Optional<Boolean> free,
                                   Optional<List<String>> scopes,
                                   Optional<List<String>> locations,
                                   Optional<List<String>> weekdays,
                                   Optional<Integer> education,
                                   Optional<Integer> experience,
                                   Optional<Integer> seniority,
                                   Optional<Integer> ageMin,
                                   Optional<Integer> ageMax,
                                   Optional<String> gender,
                                   Optional<Integer> page,
                                   String city);
    AdvertDetailsDto getAdvert(Long advertId) throws NotFoundException;
}
