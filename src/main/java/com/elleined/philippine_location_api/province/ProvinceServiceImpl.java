package com.elleined.philippine_location_api.province;

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
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Override
    public Pageable<Province> getAllBy(int regionId,
                                       String name,
                                       PageRequest request) {

        int totalElements = provinceRepository.findAllByTotal(regionId, name);
        List<Province> provinces = provinceRepository.findAllBy(regionId, name, request.page(), request.size());
        return new Pageable<>(provinces, request, totalElements);
    }
}
