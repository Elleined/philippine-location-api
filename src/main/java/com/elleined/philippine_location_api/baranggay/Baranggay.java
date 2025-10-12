package com.elleined.philippine_location_api.baranggay;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Baranggay(@JsonProperty("id") Long id,
                        @JsonProperty("name") String name,
                        @JsonProperty("city_id") Long cityId) {
}
