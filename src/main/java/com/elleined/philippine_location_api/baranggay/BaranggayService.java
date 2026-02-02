package com.elleined.philippine_location_api.baranggay;


import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface BaranggayService {
    List<BaranggayDTO> getAll(@NotNull Long regionId,
                              @NotNull Long provinceId,
                              @NotNull Long cityId,
                              String name,
                              @NotNull PageRequest request);

    int getAllTotal(@NotNull Long regionId,
                    @NotNull Long provinceId,
                    @NotNull Long cityId,
                    String name);
}
