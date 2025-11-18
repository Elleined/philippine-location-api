package com.elleined.philippine_location_api.baranggay;

import java.util.List;

public interface BaranggayRepository {

    List<Baranggay> findAll(Long regionId,
                            Long provinceId,
                            Long cityId,
                            String name,
                            int page,
                            int size);

    Integer findAllTotal(Long regionId,
                         Long provinceId,
                         Long cityId,
                         String name);
}