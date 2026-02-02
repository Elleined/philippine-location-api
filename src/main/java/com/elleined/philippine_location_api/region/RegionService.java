package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface RegionService {
    List<RegionDTO> getAll(String name,
                           @NotNull PageRequest request);

    int getAllTotal(String name);
}
