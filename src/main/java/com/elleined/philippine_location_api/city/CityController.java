package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public Pageable<CityDTO> getAll(@PathVariable("regionId") Long regionId,
                                    @PathVariable("provinceId") Long provinceId,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "5") int size) {

        PageRequest request = PageRequest.of(page, size);
        int totalElements = cityService.getAllTotal(regionId, provinceId, name);
        List<CityDTO> cities = cityService.getAll(regionId, provinceId, name, request);

        return Pageable.of(cities, request, totalElements);
    }
}
