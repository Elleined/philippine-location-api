package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import jakarta.validation.constraints.NotNull;

public interface CityService {
    Pageable<City> getAll(@NotNull Long regionId,
                          @NotNull Long provinceId,
                          String name,
                          @NotNull PageRequest request);
}
