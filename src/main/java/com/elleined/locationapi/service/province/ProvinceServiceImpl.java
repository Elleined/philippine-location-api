package com.elleined.locationapi.service.province;

import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.exception.AlreadyExistsException;
import com.elleined.locationapi.exception.ResourceNotFoundException;
import com.elleined.locationapi.mapper.ProvinceMapper;
import com.elleined.locationapi.model.Province;
import com.elleined.locationapi.model.Region;
import com.elleined.locationapi.repository.ProvinceRepository;
import com.elleined.locationapi.service.region.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;

    private final RegionService regionService;
    @Override
    public Province save(ProvinceDTO provinceDTO) {
        if (isAlreadyExists(provinceDTO)) throw new AlreadyExistsException("One of the provided id already exists!");
        Region region = regionService.getById(provinceDTO.getRegionId());
        Province province = provinceMapper.toEntity(provinceDTO, region);
        provinceRepository.save(province);
        log.debug("Province with id of {} and with name of {} saved successfully", province.getId(), province.getLocationName());
        return province;
    }

    @Override
    public List<Province> saveAll(List<ProvinceDTO> provinceDTOS) {
        return provinceDTOS.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public Province getById(int id) throws ResourceNotFoundException {
        return provinceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Province with id of " + id + " does not exists"));
    }

    @Override
    public boolean isAlreadyExists(ProvinceDTO provinceDTO) {
        return provinceRepository.existsById(provinceDTO.getId());
    }

    @Override
    public List<Province> searchByLocationName(String locationName) {
        return provinceRepository.searchByLocationName(locationName).stream()
                .sorted(Comparator.comparing(Province::getLocationName))
                .toList();
    }

    @Override
    public List<Province> getAllBy(Region region) {
        return region.getProvinces().stream()
                .sorted(Comparator.comparing(Province::getLocationName))
                .toList();
    }
}
