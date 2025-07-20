package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public interface ProvinceService {
    List<Province> getAll(@Positive int regionId);

    Page<Province> getAll(@Positive int regionId,
                          @NotNull PageRequest request);

    List<Province> searchByName(@Positive int regionId,
                                @NotBlank String name);

    Page<Province> searchByName(@Positive int regionId,
                                @NotBlank String name,
                                @NotNull PageRequest request);
}
