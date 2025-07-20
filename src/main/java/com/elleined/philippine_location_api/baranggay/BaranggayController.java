package com.elleined.philippine_location_api.baranggay;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions/{regionId}/provinces/{provinceId}/cities/{cityId}/baranggays")
@RequiredArgsConstructor
public class BaranggayController {
    private final BaranggayService baranggayService;

    @GetMapping
    public List<BaranggayDTO> getAll(@PathVariable("regionId") int regionId,
                                  @PathVariable("provinceId") int provinceId,
                                  @PathVariable("cityId") int cityId) {

        return baranggayService.getAll(regionId, provinceId, cityId);
    }

    @GetMapping("/paged")
    public Page<Baranggay> getAll(@PathVariable("regionId") int regionId,
                                  @PathVariable("provinceId") int provinceId,
                                  @PathVariable("cityId") int cityId,
                                  @RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return baranggayService.getAll(regionId, provinceId, cityId, pageRequest);
    }

    @GetMapping("/search")
    public List<Baranggay> searchByName(@PathVariable("regionId") int regionId,
                                        @PathVariable("provinceId") int provinceId,
                                        @PathVariable("cityId") int cityId,
                                        @RequestParam("name") String name) {

        return baranggayService.searchByName(regionId, provinceId, cityId, name.toLowerCase());
    }

    @GetMapping("/search/paged")
    public Page<Baranggay> searchByName(@PathVariable("regionId") int regionId,
                                        @PathVariable("provinceId") int provinceId,
                                        @PathVariable("cityId") int cityId,
                                        @RequestParam("name") String name,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return baranggayService.searchByName(regionId, provinceId, cityId, name.toLowerCase(), pageRequest);
    }
}
