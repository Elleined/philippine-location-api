package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public Pageable<City> getAllBy(Long regionId,
                                   Long provinceId,
                                   String name,
                                   PageRequest request) {

        int totalElements = cityRepository.findAllByTotal(regionId, provinceId, name);
        List<City> cities = cityRepository.findAllBy(regionId, provinceId, name, request.page(), request.size());

        return new Pageable<>(cities, request, totalElements);
    }
}
