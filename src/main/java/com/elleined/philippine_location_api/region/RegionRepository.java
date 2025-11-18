package com.elleined.philippine_location_api.region;


import java.util.List;

public interface RegionRepository {

    List<Region> findAll(String name,
                         int page,
                         int size);

    Integer findAllTotal(String name);
}