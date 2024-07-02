package com.elleined.philippinelocationapi.model.province;


import com.elleined.philippinelocationapi.model.Location;
import com.elleined.philippinelocationapi.model.city.City;
import com.elleined.philippinelocationapi.model.region.Region;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Set;

@Cacheable
@org.hibernate.annotations.Cache(region = "provinceCache", usage = CacheConcurrencyStrategy.READ_WRITE)

@Entity
@Table(
        name = "tbl_province",
        indexes = @Index(name = "name_idx", columnList = "name")
)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Province extends Location {

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "region_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Region region;

    @OneToMany(mappedBy = "province")
    private Set<City> cities;

    public boolean has(City city) {
        return this.getCities().contains(city);
    }
}
