package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities")
public class CityController {
    private final CityService cityService;

    @GetMapping
    public Pageable<City> searchByName(@PathVariable("regionId") int regionId,
                                       @PathVariable("provinceId") int provinceId,
                                       @RequestParam(value = "name", defaultValue = "", required = false) String name,
                                       @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                       @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        PageRequest request = PageRequest.of(page, size);
        return cityService.findAllBy(regionId, provinceId, name, request);
    }
}
