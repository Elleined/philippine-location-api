package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface CityService {
    Pageable<City> findAllBy(@Positive int regionId,
                             @Positive int provinceId,
                             @NotBlank String name,
                             @NotNull PageRequest request);
}
