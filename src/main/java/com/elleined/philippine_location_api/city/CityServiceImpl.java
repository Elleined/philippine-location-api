package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.PageRequest;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "city:getAll", key = "#regionId + '-' + #provinceId + '-' + #name + '-' + #request.page + '-' + #request.size")
    public List<CityDTO> getAll(Long regionId,
                                Long provinceId,
                                String name,
                                PageRequest request) {

        return cityRepository.findAll(regionId, provinceId, name, request.page(), request.size());
    }

    @Override
    @Cacheable(value = "city:getAllTotal", key = "#regionId + '-' + #provinceId + '-' + #name")
    public int getAllTotal(Long regionId, Long provinceId, String name) {
        return cityRepository.findAllTotal(regionId, provinceId, name);
    }
}
