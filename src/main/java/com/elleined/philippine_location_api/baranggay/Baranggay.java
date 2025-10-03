package com.elleined.philippine_location_api.baranggay;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Baranggay(Long id,
                        String name,
                        @JsonProperty("city_id") Long cityId) {
}
