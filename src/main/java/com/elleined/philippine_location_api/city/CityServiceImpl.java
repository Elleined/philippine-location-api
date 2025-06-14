package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
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
    public List<City> getAll(int regionId,
                             int provinceId) {

        return cityRepository.findAll(regionId, provinceId);
    }

    @Override
    public Page<City> getAll(int regionId,
                             int provinceId,
                             PageRequest request) {

        List<City> cities = cityRepository.findAll(regionId, provinceId, request.getOffset(), request.size());
        return new Page<>(cities, request, cityRepository.findAllTotal(regionId, provinceId));
    }

    @Override
    public List<City> searchByName(int regionId,
                                   int provinceId,
                                   String name) {

        return cityRepository.searchByName(regionId, provinceId, name);
    }

    @Override
    public Page<City> searchByName(int regionId,
                                   int provinceId,
                                   String name,
                                   PageRequest request) {

        List<City> cities = cityRepository.searchByName(regionId, provinceId, name, request.getOffset(), request.size());
        return new Page<>(cities, request, cityRepository.searchByNameTotal(regionId, provinceId, name));
    }
}
