package com.elleined.philippine_location_api.baranggay;


import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import jakarta.validation.constraints.NotNull;

public interface BaranggayService {
    Pageable<Baranggay> getAllBy(@NotNull Long regionId,
                                 @NotNull Long provinceId,
                                 @NotNull Long cityId,
                                 String name,
                                 @NotNull PageRequest request);
}
