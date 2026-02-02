package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ProvinceService {
    List<ProvinceDTO> getAll(@NotNull Long regionId,
                             String name,
                             @NotNull PageRequest request);

    int getAllTotal(@NotNull Long regionId, String name);
}
