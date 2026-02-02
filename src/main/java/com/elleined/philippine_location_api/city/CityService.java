package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface CityService {

    List<CityDTO> getAll(@NotNull Long regionId,
                         @NotNull Long provinceId,
                         String name,
                         @NotNull PageRequest request);

    int getAllTotal(@NotNull Long regionId,
                    @NotNull Long provinceId,
                    String name);
}
