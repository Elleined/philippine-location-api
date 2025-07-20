package com.elleined.philippine_location_api.region;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public record RegionDTO(
        @Id Long id,
        String name,
        String description
) implements Serializable {
}
