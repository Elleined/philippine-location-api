package com.elleined.philippine_location_api.city;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Table;

@Table("city")
public record City(@JsonProperty("id") Long id,
                   @JsonProperty("name") String name,
                   @JsonProperty("region_id") Long regionId,
                   @JsonProperty("province_id") Long provinceId) {

    public static final String FK_ID = "city_id";
}
