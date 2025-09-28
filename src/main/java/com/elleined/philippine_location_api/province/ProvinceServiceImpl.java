package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@Transactional
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public Pageable<Province> getAllBy(Long regionId,
                                       String name,
                                       PageRequest request) {

        int totalElements = provinceRepository.findAllByTotal(regionId, name);
        List<Province> provinces = provinceRepository.findAllBy(regionId, name, request.page(), request.size());
        return new Pageable<>(provinces, request, totalElements);
    }
}
