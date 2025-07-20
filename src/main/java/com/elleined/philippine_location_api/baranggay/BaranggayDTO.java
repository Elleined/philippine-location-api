package com.elleined.philippine_location_api.baranggay;

import java.io.Serializable;

public record BaranggayDTO(
        Long id,
        String name,
        Long cityId
) implements Serializable {
}
