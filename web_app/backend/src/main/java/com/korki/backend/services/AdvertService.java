package com.korki.backend.services;

import com.korki.backend.dtos.advert_dtos.AdvertCreateDto;
import com.korki.backend.dtos.user_dtos.SecurityUser;
import com.korki.backend.dtos.advert_dtos.AdvertDetailsDto;
import com.korki.backend.dtos.advert_dtos.AdvertDisplayDto;
import com.korki.backend.dtos.advert_dtos.AdvertDto;
import com.korki.backend.exceptions.NoAccessException;
import com.korki.backend.exceptions.NotFoundException;
import com.korki.backend.services.interfaces.IAdvertService;
import com.korki.backend.utills.mappers.IMapper;
import com.korki.common.models.Advert;
import com.korki.common.repositories.AdvertRepository;
import com.korki.common.repositories.TeacherRepository;
import com.korki.common.repositories.specifications.AdvertSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertService implements IAdvertService {

    private final IMapper mapper;

    private final TeacherRepository teacherRepository;
    private final AdvertRepository advertRepository;

    @Override
    public void createAdvert(AdvertCreateDto dto, SecurityUser loggedUser) {
        var teacherEntityWithAdverts = teacherRepository
                .getTeacherWithAdverts(loggedUser
                        .getUser()
                        .getTeacher()
                        .getId());
        var advertEntity = new Advert();

        mapper.getAdvertMapper()
                .mapToEntity
                .accept(dto, advertEntity);
        teacherEntityWithAdverts.getAdverts().add(advertEntity);
        advertEntity.setTeacher(teacherEntityWithAdverts);

        advertRepository.save(advertEntity);
        teacherRepository.save(teacherEntityWithAdverts);
    }

    @Override
    public List<AdvertDisplayDto> getProfileAdverts(SecurityUser securityUser) {
        var teacherEntity = securityUser.getUser().getTeacher();
        var advertEntity = advertRepository.getAdvertsByTeacherId(teacherEntity.getId());

        return advertEntity.stream()
                .map(advert -> mapper.getAdvertMapper()
                        .mapToAdvertDisplay
                        .apply(advert, teacherEntity))
                .toList();
    }

    @Override
    public void updateAdvert(AdvertCreateDto dto, Long advertId) throws NotFoundException {
        var advertEntity = advertRepository
                .findById(advertId)
                .orElseThrow(() -> new NotFoundException("Couldn't find advert with id: " + advertId));
        mapper.getAdvertMapper()
                .mapToEntity
                .accept(dto, advertEntity);

        advertRepository.save(advertEntity);
    }

    @Override
    public AdvertCreateDto getAdvertEditDetails(Long advertId) throws NotFoundException {
        var advertEntity = advertRepository
                .findById(advertId)
                .orElseThrow(() -> new NotFoundException("Couldn't find advert with id: " + advertId));
        return mapper.getAdvertMapper()
                .mapToAdvertCreate
                .apply(advertEntity);
    }

    @Override
    public void deleteAdvert(Long advertId, SecurityUser loggedUser) throws NotFoundException, NoAccessException {
        var advertEntity = advertRepository
                .findById(advertId)
                .orElseThrow(() -> new NotFoundException("Couldn't find advert with id: " + advertId));
        var teacherEntity = loggedUser.getUser().getTeacher();

        if (advertEntity.getTeacher() == teacherEntity){
            advertRepository.delete(advertEntity);
        }else {
            throw new NoAccessException("You can't delete that advert!");
        }
    }

    @Override
    public AdvertDetailsDto getAdvert(Long advertId) throws NotFoundException {
        var advert = advertRepository.findById(advertId)
                .orElseThrow(() -> new NotFoundException("Couldn't find advert!"));
        return mapper.getAdvertMapper().mapToAdvertDetails.apply(advert);
    }
    @Override
    public List<AdvertDto> getCityAdverts(Optional<Float> price,
                                          Optional<Boolean> free,
                                          Optional<List<String>> scopes,
                                          Optional<List<String>> locations,     //TODO
                                          Optional<List<String>> weekdays,
                                          Optional<Integer> education,
                                          Optional<Integer> experience,
                                          Optional<Integer> seniority,
                                          Optional<Integer> ageMin,
                                          Optional<Integer> ageMax,
                                          Optional<String> gender,
                                          Optional<Integer> page,
                                          String city){

        List<Specification<Advert>> specifications = new ArrayList<>();
        addSpecifications( price, free, scopes, locations, weekdays, education, experience, seniority,ageMin, ageMax, gender, city, specifications);

        final int ITEMS_PER_PAGE = 10;
        final Pageable advertPage;
        advertPage = page.map(p -> PageRequest.of(p, ITEMS_PER_PAGE))
                .orElseGet(() ->  PageRequest.of(0, ITEMS_PER_PAGE));
        return advertRepository
                .findAll(Specification.allOf(specifications), advertPage)
                .getContent()
                .stream()
                .map(mapper.getAdvertMapper().mapToAdvertDto)
                .toList();
    }



    private void addSpecifications(Optional<Float> price,
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
                                   String city,
                                   List<Specification<Advert>> specifications){
        price.ifPresent(p -> specifications
                .add(AdvertSpecification.priceLessOrEqualsThan(p)));
        free.ifPresent(f -> specifications
                .add(AdvertSpecification.hasFreeLesson()));
        scopes.ifPresent(s -> specifications
                .add(AdvertSpecification.isInTeachingScope(s)));
        locations.ifPresent(l -> specifications
                .add(AdvertSpecification.isInLocation(l)));
        weekdays.ifPresent(w -> specifications
                .add(AdvertSpecification.isInWeekdays(w)));
        education.ifPresent(e -> specifications
                .add(AdvertSpecification.hasMinEducation(e)));
        experience.ifPresent(e -> specifications
                .add(AdvertSpecification.hasMinExperience(e)));
        seniority.ifPresent(s -> specifications
                .add(AdvertSpecification.hasMinSeniority(s)));
        ageMin.ifPresent(a -> specifications
                .add(AdvertSpecification.olderThan(a)));
        ageMax.ifPresent(a -> specifications
                .add(AdvertSpecification.youngerThan(a)));
        gender.ifPresent(g -> specifications
                .add(AdvertSpecification.gender(g)));
        specifications.add(AdvertSpecification.city(city));
    }
}
