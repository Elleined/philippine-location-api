package com.elleined.philippine_location_api.region;


import java.util.List;

public interface RegionRepository {

    List<Region> findAllBy(String name,
                           int page,
                           int size);

    Integer findAllByTotal(String name);
}