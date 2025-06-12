package com.elleined.philippine_location_api.baranggay;

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
public class BaranggayServiceImpl implements BaranggayService {
    private final BaranggayRepository baranggayRepository;

    @Override
    public List<Baranggay> getAll(int regionId,
                                  int provinceId,
                                  int cityId) {

        return baranggayRepository.findAll(regionId, provinceId, cityId);
    }

    @Override
    public Page<Baranggay> getAll(int regionId,
                                  int provinceId,
                                  int cityId,
                                  PageRequest request) {

        List<Baranggay> baranggays = baranggayRepository.findAll(regionId, provinceId, cityId, request.getOffset(), request.size());
        return new Page<>(baranggays, request, baranggayRepository.findAllTotal(regionId, provinceId, cityId));
    }

    @Override
    public List<Baranggay> searchByName(int regionId,
                                        int provinceId,
                                        int cityId,
                                        String name) {

        return baranggayRepository.searchByName(regionId, provinceId, cityId, name);
    }

    @Override
    public Page<Baranggay> searchByName(int regionId,
                                        int provinceId,
                                        int cityId,
                                        String name,
                                        PageRequest request) {

        List<Baranggay> baranggays = baranggayRepository.searchByName(regionId, provinceId, cityId, name, request.getOffset(), request.size());
        return new Page<>(baranggays, request, baranggayRepository.searchByNameTotal(regionId, provinceId, cityId, name));
    }
}
