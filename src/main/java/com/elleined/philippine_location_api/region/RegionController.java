package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping
    public List<Region> getAll() {
        return regionService.getAll();
    }

    @GetMapping("/paged")
    public Page<Region> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return regionService.getAll(pageRequest);
    }

    @GetMapping("/search")
    public List<Region> searchByName(@RequestParam("name") String name) {
        return regionService.searchByName(name);
    }

    @GetMapping("/paged-search")
    public Page<Region> searchByName(@RequestParam("name") String name,
                                     @RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return regionService.searchByName(pageRequest, name);
    }
}
