package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface RegionService {
    List<Region> getAll();

    Page<Region> getAll(@NotNull PageRequest request);

    List<Region> searchByName(@NotBlank String name);

    Page<Region> searchByName(@NotBlank String name,
                              @NotNull PageRequest request);
}
