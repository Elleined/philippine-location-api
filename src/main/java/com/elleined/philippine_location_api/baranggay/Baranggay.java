package com.elleined.philippine_location_api.baranggay;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Table;

@Table("baranggay")
public record Baranggay(@JsonProperty("id") Long id,
                        @JsonProperty("name") String name,
                        @JsonProperty("region_id") Long regionId,
                        @JsonProperty("province_id") Long provinceId,
                        @JsonProperty("city_id") Long cityId) {
}
