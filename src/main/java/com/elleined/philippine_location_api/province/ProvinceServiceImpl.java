package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Override
    @Cacheable(value = "provinces", key = "#regionId")
    public List<ProvinceDTO> getAll(int regionId) {
        return provinceRepository.findAll(regionId).stream()
                .map(Province::toDTO)
                .toList();
    }

    @Override
    public Page<Province> getAll(int regionId,
                                 PageRequest request) {

        List<Province> provinces = provinceRepository.findAll(regionId, request.getOffset(), request.size());
        return new Page<>(provinces, request, provinceRepository.findAllTotal(regionId));
    }

    @Override
    public List<Province> searchByName(int regionId,
                                       String name) {

        return provinceRepository.searchByName(regionId, name);
    }

    @Override
    public Page<Province> searchByName(int regionId,
                                       String name,
                                       PageRequest request) {

        List<Province> provinces = provinceRepository.searchByName(regionId, name, request.getOffset(), request.size());
        return new Page<>(provinces, request, provinceRepository.searchByNameTotal(regionId, name));
    }
}
