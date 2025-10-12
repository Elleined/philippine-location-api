package com.elleined.philippine_location_api.region;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Region(@JsonProperty("id") Long id,
                     @JsonProperty("name") String name,
                     @JsonProperty("description") String description) {

    public static final String FK_ID = "region_id";
}
