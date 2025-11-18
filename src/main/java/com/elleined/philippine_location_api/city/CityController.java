package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public Pageable<City> getAll(@PathVariable("regionId") Long regionId,
                                 @PathVariable("provinceId") Long provinceId,
                                 @RequestParam(value = "name", defaultValue = "", required = false) String name,
                                 @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                 @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        PageRequest request = PageRequest.of(page, size);
        return cityService.getAll(regionId, provinceId, name, request);
    }
}
