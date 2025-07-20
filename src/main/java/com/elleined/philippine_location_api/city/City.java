package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.province.Province;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("city")
public record City(@Id Long id,
                   String name,
                   @Column("province_id") AggregateReference<Province, Long> province) {

    public CityDTO toDTO() {
        return new CityDTO(this.id(), this.name(), this.province().getId());
    }
}
