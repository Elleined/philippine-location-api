package com.elleined.philippine_location_api.province;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;

public record ProvinceDTO(@Column("id") @JsonProperty("id") Long id,
                          @Column("name") @JsonProperty("name") String name,
                          @Column("region_id") @JsonProperty("region_id") Long regionId,
                          @Column("region_name") @JsonProperty("region_name") String regionName) implements Serializable {
}
