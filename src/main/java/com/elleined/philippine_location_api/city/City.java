package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.province.Province;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;

public record City(@Id Long id,
                   String name,
                   @Column("province_id") AggregateReference<Province, Long> province) {
}
