package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping
    public Pageable<Region> findAllBy(@RequestParam(value = "name", defaultValue = "", required = false) String name,
                                      @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                      @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return regionService.findAllBy(name.toLowerCase(Locale.ROOT), pageRequest);
    }
}
