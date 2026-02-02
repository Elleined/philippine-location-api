package com.elleined.philippine_location_api.baranggay;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities/{cityId}/baranggays")
public class BaranggayController {
    private final BaranggayService baranggayService;

    public BaranggayController(BaranggayService baranggayService) {
        this.baranggayService = baranggayService;
    }

    @GetMapping
    public Pageable<BaranggayDTO> getAll(@PathVariable("regionId") Long regionId,
                                         @PathVariable("provinceId") Long provinceId,
                                         @PathVariable("cityId") Long cityId,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "5") int size) {

        PageRequest request = PageRequest.of(page, size);
        int totalElements = baranggayService.getAllTotal(regionId, provinceId, cityId, name);
        List<BaranggayDTO> baranggays = baranggayService.getAll(regionId, provinceId, cityId, name, request);

        return Pageable.of(baranggays, request, totalElements);
    }
}
