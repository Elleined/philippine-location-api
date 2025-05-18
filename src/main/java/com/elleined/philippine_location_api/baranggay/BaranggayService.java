package com.elleined.philippine_location_api.baranggay;


import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public interface BaranggayService {
    List<Baranggay> getAll(@Positive int regionId,
                           @Positive int provinceId,
                           @Positive int cityId);

    Page<Baranggay> getAll(@Positive int regionId,
                           @Positive int provinceId,
                           @Positive int cityId,
                           @NotNull PageRequest request);

    List<Baranggay> searchByName(@Positive int regionId,
                                 @Positive int provinceId,
                                 @Positive int cityId,
                                 @NotBlank String name);

    Page<Baranggay> searchByName(@Positive int regionId,
                                 @Positive int provinceId,
                                 @Positive int cityId,
                                 @NotBlank String name,
                                 @NotNull PageRequest request);
}
