package com.elleined.philippine_location_api.region;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("region")
public record Region(@Id Long id,
                     String name,
                     String description) {


    public RegionDTO toDTO() {
        return new RegionDTO(this.id(), this.name(), this.description());
    }
}
