package com.elleined.philippine_location_api.baranggay;

import com.elleined.philippine_location_api.city.City;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("baranggay")
public record Baranggay(@Id Long id,
                        String name,
                        @Column("city_id") AggregateReference<City, Long> city) {
}
