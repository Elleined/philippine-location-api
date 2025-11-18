package com.elleined.philippine_location_api.city;

import java.util.List;

public interface CityRepository {

    List<City> findAll(Long regionId,
                       Long provinceId,
                       String name,
                       int page,
                       int size);

    Integer findAllTotal(Long regionId,
                         Long provinceId,
                         String name);
}