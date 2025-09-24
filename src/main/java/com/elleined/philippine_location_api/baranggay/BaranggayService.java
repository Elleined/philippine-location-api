package com.elleined.philippine_location_api.baranggay;


import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface BaranggayService {
    Pageable<Baranggay> getAllBy(@Positive int regionId,
                                 @Positive int provinceId,
                                 @Positive int cityId,
                                 String name,
                                 @NotNull PageRequest request);
}
