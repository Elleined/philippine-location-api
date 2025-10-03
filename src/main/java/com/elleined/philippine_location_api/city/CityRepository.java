package com.elleined.philippine_location_api.city;

import java.util.List;

public interface CityRepository {

    List<City> findAllBy(Long regionId,
                         Long provinceId,
                         String name,
                         int page,
                         int size);

    Integer findAllByTotal(Long regionId,
                       Long provinceId,
                       String name);
}