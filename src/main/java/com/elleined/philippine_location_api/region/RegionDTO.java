package com.elleined.philippine_location_api.region;

import java.io.Serializable;

public record RegionDTO(
        Long id,
        String name,
        String description
) implements Serializable {
}
