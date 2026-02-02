package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.paging.PageRequest;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "province:getAll", key = "#regionId + '-' + #name + '-' + #request.page + '-' + #request.size")
    public List<ProvinceDTO> getAll(Long regionId,
                                    String name,
                                    PageRequest request) {

        return provinceRepository.findAll(regionId, name, request.page(), request.size());
    }

    @Override
    @Cacheable(value = "province:getAllTotal", key = "#regionId + '-' + #name")
    public int getAllTotal(Long regionId, String name) {
        return provinceRepository.findAllTotal(regionId, name);
    }
}
