package com.korki.backend.services.interfaces;


import com.korki.backend.dtos.AdvertDetailsDto;
import com.korki.backend.dtos.AdvertDto;
import com.korki.backend.dtos.SecurityUser;

import java.util.List;

public interface IAdvertService {

    void createAdvert(AdvertDetailsDto dto, SecurityUser loggedUser);
}
