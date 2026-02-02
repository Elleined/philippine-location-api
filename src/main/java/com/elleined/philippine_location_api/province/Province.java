package com.elleined.philippine_location_api.province;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.relational.core.mapping.Table;

@Table("province")
public record Province(@JsonProperty("id") Long id,
                       @JsonProperty("name") String name,
                       @JsonProperty("region_id") Long regionId) {

    public static final String FK_ID = "province_id";
}
