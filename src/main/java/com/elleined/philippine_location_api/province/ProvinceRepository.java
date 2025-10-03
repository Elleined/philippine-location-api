package com.elleined.philippine_location_api.province;

import java.util.List;

public interface ProvinceRepository {

    List<Province> findAllBy(Long regionId,
                             String name,
                             int page,
                             int size);

    Integer findAllByTotal(Long regionId,
                       String name);
}