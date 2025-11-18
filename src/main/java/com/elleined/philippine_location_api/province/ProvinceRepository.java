package com.elleined.philippine_location_api.province;

import java.util.List;

public interface ProvinceRepository {

    List<Province> findAll(Long regionId,
                           String name,
                           int page,
                           int size);

    Integer findAllTotal(Long regionId,
                         String name);
}