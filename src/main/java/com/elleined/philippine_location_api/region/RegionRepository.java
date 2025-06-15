package com.elleined.philippine_location_api.region;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Integer> {

    @Override
    @Query("CALL region_get_all()")
    List<Region> findAll();

    @Query("CALL region_get_all_paginated(:page, :size)")
    List<Region> findAll(@Param("page") int page,
                         @Param("size") int size);

    @Query("CALL ")
    int findAllTotal();

    @Query("CALL region_search_by_name(:name)")
    List<Region> searchByName(@Param("name") String name);

    @Query("CALL region_search_by_name_paginated(:name, :page, :size)")
    List<Region> searchByName(@Param("name") String name,
                              @Param("page") int page,
                              @Param("size") int size);

    @Query("CALL region_search_by_total(:name)")
    int searchByNameTotal(@Param("name") String name);
}