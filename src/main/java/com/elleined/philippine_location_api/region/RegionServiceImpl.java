package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    @Override
    public Page<Region> getAll(@NotNull PageRequest request) {
        List<Region> regions = regionRepository.findAll(request.getOffset(), request.size());
        return new Page<>(regions, request, regionRepository.findAllTotal());
    }

    @Override
    public List<Region> searchByName(@NotBlank String name) {
        return regionRepository.searchByName(name);
    }

    @Override
    public Page<Region> searchByName(@NotBlank String name,
                                     @NotNull PageRequest request) {

        List<Region> regions = regionRepository.searchByName(name, request.getOffset(), request.size());
        return new Page<>(regions, request, regionRepository.searchByNameTotal(name));
    }
}
