package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.PageRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@Transactional
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    @Cacheable(value = "region:getAll", key = "#name + '-' + #request.page + '-' + #request.size")
    public List<RegionDTO> getAll(String name, PageRequest request) {
        return regionRepository.findAll(name, request.page(), request.size());
    }

    @Override
    public int getAllTotal(String name) {
        return regionRepository.findAllTotal(name );
    }
}
