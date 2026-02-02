package com.elleined.philippine_location_api.region;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;

public record RegionDTO(@Column("id") @JsonProperty("id") Long id,
                        @Column("name") @JsonProperty("name") String name,
                        @Column("description") @JsonProperty("description") String description) implements Serializable {
}
