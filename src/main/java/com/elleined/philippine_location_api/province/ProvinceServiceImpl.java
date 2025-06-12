package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Override
    public List<Province> getAll(int regionId) {
        return provinceRepository.findAll(regionId);
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
