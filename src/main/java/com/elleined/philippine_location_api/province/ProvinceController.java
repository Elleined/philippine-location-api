package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions/{regionId}/provinces")
public class ProvinceController {
    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping
    public Pageable<ProvinceDTO> getAllBy(@PathVariable("regionId") Long regionId,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "page", defaultValue = "1") int page,
                                          @RequestParam(value = "size", defaultValue = "5") int size) {

        PageRequest request = PageRequest.of(page, size);
        int totalElements = provinceService.getAllTotal(regionId, name);
        List<ProvinceDTO> provinces = provinceService.getAll(regionId, name, request);

        return Pageable.of(provinces, request, totalElements);
    }
}
