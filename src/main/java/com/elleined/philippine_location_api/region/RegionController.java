package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public Pageable<RegionDTO> getAll(@RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "size", defaultValue = "5") int size) {

        PageRequest request = PageRequest.of(page, size);
        int totalElements = regionService.getAllTotal(name);
        List<RegionDTO> regions = regionService.getAll(name, request);

        return Pageable.of(regions, request, totalElements);
    }
}
