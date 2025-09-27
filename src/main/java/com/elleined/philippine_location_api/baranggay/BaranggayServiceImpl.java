package com.elleined.philippine_location_api.baranggay;

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
public class BaranggayServiceImpl implements BaranggayService {
    private final BaranggayRepository baranggayRepository;

    @Override
    public Pageable<Baranggay> getAllBy(Long regionId,
                                        Long provinceId,
                                        Long cityId,
                                        String name,
                                        PageRequest request) {

        int totalElements = baranggayRepository.findAllByTotal(regionId, provinceId, cityId, name);
        List<Baranggay> baranggays = baranggayRepository.findAllBy(regionId, provinceId, cityId, name, request.page(), request.size());
        return Pageable.of(baranggays, request, totalElements);
    }
}
