package com.elleined.philippine_location_api.province;


import com.elleined.philippine_location_api.region.Region;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("province")
public record Province(@Id Long id,
                       String name,
                       @Column("region_id") AggregateReference<Region, Long> region) {

    public ProvinceDTO toDTO() {
        return new ProvinceDTO(this.id(), this.name(), this.region().getId());
    }
}
