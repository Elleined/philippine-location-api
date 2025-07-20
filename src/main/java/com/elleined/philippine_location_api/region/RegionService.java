package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface RegionService {
    List<RegionDTO> getAll();

    Page<RegionDTO> getAll(@NotNull PageRequest request);

    List<RegionDTO> searchByName(@NotBlank String name);

    Page<RegionDTO> searchByName(@NotNull PageRequest request,
                              @NotBlank String name);
}
