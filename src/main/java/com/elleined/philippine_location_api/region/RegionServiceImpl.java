package com.elleined.philippine_location_api.region;

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
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    @Override
    public Pageable<Region> findAllBy(String name,
                                      PageRequest request) {

        int totalElements = regionRepository.findAllByTotal(name);
        List<Region> regions = regionRepository.findAllBy(name, request.page(), request.size());
        return new Pageable<>(regions, request, totalElements);
    }
}
