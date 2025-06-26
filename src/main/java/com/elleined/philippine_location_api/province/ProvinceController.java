package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions/{regionId}/provinces")
@RequiredArgsConstructor
public class ProvinceController {
    private final ProvinceService provinceService;

    @GetMapping
    public List<Province> getAll(@PathVariable("regionId") int regionId) {
        return provinceService.getAll(regionId);
    }

    @GetMapping("/paged")
    public Page<Province> getAll(@PathVariable("regionId") int regionId,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return provinceService.getAll(regionId, pageRequest);
    }

    @GetMapping("/search")
    public List<Province> searchByName(@PathVariable("regionId") int regionId,
                                       @RequestParam("name") String name) {

        return provinceService.searchByName(regionId, name);
    }

    @GetMapping("/search/paged")
    public Page<Province> searchByName(@PathVariable("regionId") int regionId,
                                       @RequestParam("name") String name,
                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return provinceService.searchByName(regionId, name, pageRequest);
    }
}
