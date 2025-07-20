package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public interface CityService {
    List<CityDTO> getAll(@Positive int regionId,
                      @Positive int provinceId);

    Page<City> getAll(@Positive int regionId,
                      @Positive int provinceId,
                      @NotNull PageRequest request);

    List<City> searchByName(@Positive int regionId,
                            @Positive int provinceId,
                            @NotBlank String name);

    Page<City> searchByName(@Positive int regionId,
                            @Positive int provinceId,
                            @NotBlank String name,
                            @NotNull PageRequest request);
}
