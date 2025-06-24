package com.elleined.philippine_location_api.province;

import java.io.Serializable;

public record ProvinceDTO(
        Long id,
        String name,
        Long regionId
) implements Serializable {
}
