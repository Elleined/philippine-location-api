package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
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
    public Pageable<Region> getAll(PageRequest request, String name) {
        int totalElements = regionRepository.findAllTotal(name);
        List<Region> regions = regionRepository.findAll(name, request.page(), request.size());
        return new Pageable<>(regions, request, totalElements);
    }
}
