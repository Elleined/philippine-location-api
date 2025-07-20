package com.elleined.philippine_location_api.city;

import java.io.Serializable;

public record CityDTO(
        Long id,
        String name,
        Long provinceId
) implements Serializable {
}
