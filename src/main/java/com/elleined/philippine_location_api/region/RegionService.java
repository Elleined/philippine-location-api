package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface RegionService {
    Pageable<Region> findAllBy(@NotBlank String name,
                               @NotNull PageRequest request);
}
