package com.elleined.philippine_location_api.baranggay;

import java.util.List;

public interface BaranggayRepository {

    List<Baranggay> findAllBy(Long regionId,
                              Long provinceId,
                              Long cityId,
                              String name,
                              int page,
                              int size);

    int findAllByTotal(Long regionId,
                       Long provinceId,
                       Long cityId,
                       String name);
}