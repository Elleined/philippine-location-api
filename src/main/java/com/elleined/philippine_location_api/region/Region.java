package com.elleined.philippine_location_api.region;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Table;

@Table("region")
public record Region(@JsonProperty("id") Long id,
                     @JsonProperty("name") String name,
                     @JsonProperty("description") String description) {

    public static final String FK_ID = "region_id";
}
