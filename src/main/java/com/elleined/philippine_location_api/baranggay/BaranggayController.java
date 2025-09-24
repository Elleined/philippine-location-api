package com.elleined.philippine_location_api.baranggay;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities/{cityId}/baranggays")
public class BaranggayController {
    private final BaranggayService baranggayService;

    @GetMapping
    public Pageable<Baranggay> findAllBy(@PathVariable("regionId") int regionId,
                                         @PathVariable("provinceId") int provinceId,
                                         @PathVariable("cityId") int cityId,
                                         @RequestParam(value = "name", defaultValue = "", required = false) String name,
                                         @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                         @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        PageRequest request = PageRequest.of(page, size);
        return baranggayService.getAllBy(regionId, provinceId, cityId, name, request);
    }
}
