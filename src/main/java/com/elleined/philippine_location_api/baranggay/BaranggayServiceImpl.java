package com.elleined.philippine_location_api.baranggay;

import com.elleined.philippine_location_api.paging.PageRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@Transactional
public class BaranggayServiceImpl implements BaranggayService {
    private final BaranggayRepository baranggayRepository;

    public BaranggayServiceImpl(BaranggayRepository baranggayRepository) {
        this.baranggayRepository = baranggayRepository;
    }

    @Override
    @Cacheable(value = "baranggay:getAll", key = "#regionId + '-' + #provinceId + '-' + #cityId + '-' + #name + '-' + #request.page + '-' + #request.size")
    public List<BaranggayDTO> getAll(Long regionId,
                                     Long provinceId,
                                     Long cityId,
                                     String name,
                                     PageRequest request) {

        return baranggayRepository.findAll(regionId, provinceId, cityId, name, request.page(), request.size());
    }

    @Override
    @Cacheable(value = "baranggay:getAllTotal", key = "#regionId + '-' + #provinceId + '-' + #cityId + '-' + #name")
    public int getAllTotal(Long regionId, Long provinceId, Long cityId, String name) {
        return baranggayRepository.findAllTotal(regionId, provinceId, cityId, name);
    }
}
