package com.elleined.philippine_location_api.baranggay;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities/{cityId}/baranggays")
public class BaranggayController {
    private final BaranggayService baranggayService;

    public BaranggayController(BaranggayService baranggayService) {
        this.baranggayService = baranggayService;
    }

    @GetMapping
    public Pageable<Baranggay> getAll(@PathVariable("regionId") Long regionId,
                                      @PathVariable("provinceId") Long provinceId,
                                      @PathVariable("cityId") Long cityId,
                                      @RequestParam(value = "name", defaultValue = "", required = false) String name,
                                      @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                      @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        PageRequest request = PageRequest.of(page, size);
        return baranggayService.getAll(regionId, provinceId, cityId, name, request);
    }
}
