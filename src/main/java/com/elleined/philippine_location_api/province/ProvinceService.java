package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import jakarta.validation.constraints.NotNull;

public interface ProvinceService {
    Pageable<Province> getAllBy(@NotNull Long regionId,
                                String name,
                                @NotNull PageRequest request);
}
