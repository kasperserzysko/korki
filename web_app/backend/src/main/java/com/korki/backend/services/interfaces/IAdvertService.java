package com.korki.backend.services.interfaces;


import com.korki.backend.dtos.AdvertDetailsDto;
import com.korki.backend.dtos.SecurityUser;
import com.korki.backend.dtos.advert_dtos.AdvertDisplayDto;
import com.korki.backend.exceptions.NoAccessException;
import com.korki.backend.exceptions.NotFoundException;

import java.util.List;


public interface IAdvertService {

    void createAdvert(AdvertDetailsDto dto, SecurityUser loggedUser);
    List<AdvertDisplayDto> getProfileAdverts(SecurityUser securityUser);
    void updateAdvert(AdvertDetailsDto dto, Long advertId) throws NotFoundException;
    AdvertDetailsDto getAdvertEditDetails(Long advertId) throws NotFoundException;
    void deleteAdvert(Long advertId, SecurityUser loggedUser) throws NotFoundException, NoAccessException;
}
