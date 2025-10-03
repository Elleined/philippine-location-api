package com.elleined.philippine_location_api.region;

public record Region(Long id,
                     String name,
                     String description) {

    public static final String FK_ID = "region_id";
}
