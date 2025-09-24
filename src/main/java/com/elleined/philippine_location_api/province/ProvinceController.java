package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regions/{regionId}/provinces")
@RequiredArgsConstructor
public class ProvinceController {
    private final ProvinceService provinceService;

    @GetMapping
    public Pageable<Province> searchByName(@PathVariable("regionId") int regionId,
                                           @RequestParam(value = "name", defaultValue = "", required = false) String name,
                                           @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                           @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        PageRequest request = PageRequest.of(page, size);
        return provinceService.findAllBy(regionId, name, request);
    }
}
