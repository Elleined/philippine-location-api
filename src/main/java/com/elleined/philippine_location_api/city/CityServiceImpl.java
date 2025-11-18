package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@Transactional
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Pageable<City> getAll(Long regionId,
                                 Long provinceId,
                                 String name,
                                 PageRequest request) {

        int totalElements = cityRepository.findAllTotal(regionId, provinceId, name);
        List<City> cities = cityRepository.findAll(regionId, provinceId, name, request.page(), request.size());

        return new Pageable<>(cities, request, totalElements);
    }
}
