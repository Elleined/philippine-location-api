package com.elleined.philippine_location_api.region;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Integer> {

    @Query("CALL region_find_all_by(:name, :page, :size)")
    List<Region> findAllBy(@Param("name") String name,
                           @Param("page") int page,
                           @Param("size") int size);

    @Query("CALL region_find_all_by(:name)")
    int findAllByTotal(@Param("name") String name);
}