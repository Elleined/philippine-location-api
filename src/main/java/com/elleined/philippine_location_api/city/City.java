package com.elleined.philippine_location_api.city;

import com.fasterxml.jackson.annotation.JsonProperty;

public record City(@JsonProperty("id") Long id,
                   @JsonProperty("name") String name,
                   @JsonProperty("province_id") Long provinceId) {

    public static final String FK_ID = "city_id";
}
