package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities")
public class CityController {
    private final CityService cityService;

    @GetMapping
    public List<City> getAll(@PathVariable("regionId") int regionId,
                               @PathVariable("provinceId") int provinceId) {

        return cityService.getAll(regionId, provinceId);
    }

    @GetMapping("/paged")
    public Page<City> getAll(@PathVariable("regionId") int regionId,
                               @PathVariable("provinceId") int provinceId,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return cityService.getAll(regionId, provinceId, pageRequest);
    }

    @GetMapping("/search")
    public List<City> searchByName(@PathVariable("regionId") int regionId,
                                   @PathVariable("provinceId") int provinceId,
                                   @RequestParam("name") String name) {

        return cityService.searchByName(regionId, provinceId, name);
    }

    @GetMapping("/paged-search")
    public Page<City> searchByName(@PathVariable("regionId") int regionId,
                                   @PathVariable("provinceId") int provinceId,
                                   @RequestParam("name") String name,
                                   @RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return cityService.searchByName(regionId, provinceId, name, pageRequest);
    }
}
