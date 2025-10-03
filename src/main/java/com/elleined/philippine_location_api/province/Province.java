package com.elleined.philippine_location_api.province;


import com.fasterxml.jackson.annotation.JsonProperty;

public record Province(Long id,
                       String name,
                       @JsonProperty("region_id") Long regionId) {

    public static final String FK_ID = "province_id";
}
