package com.elleined.philippine_location_api.region;

import org.springframework.data.annotation.Id;

public record Region(@Id Long id,
                     String name,
                     String description) {

    public Region(String name, String description) {
        this(null, name, description);
    }
}
