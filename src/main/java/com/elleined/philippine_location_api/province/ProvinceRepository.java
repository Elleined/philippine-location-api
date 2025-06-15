package com.elleined.philippine_location_api.province;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceRepository extends CrudRepository<Province, Integer> {

    @Query("CALL province_get_all(:regionId)")
    List<Province> findAll(@Param("regionId") int regionId);

    @Query("CALL province_get_all_paginated(:regionId, :page, :size)")
    List<Province> findAll(@Param("regionId") int regionId,
                           @Param("page") int page,
                           @Param("size") int size);

    @Query("CALL province_get_all_total(:regionId)")
    int findAllTotal(@Param("regionId") int regionId);

    @Query("CALL province_search_by_name(:regionId, :name)")
    List<Province> searchByName(@Param("regionId") int regionId,
                                @Param("name") String name);

    @Query("CALL province_search_by_name_paginated(:regionId, :name, :page, :size)")
    List<Province> searchByName(@Param("regionId") int regionId,
                                @Param("name") String name,
                                @Param("page") int page,
                                @Param("size") int size);

    @Query("CALL province_search_by_name_total(:regionId, :name)")
    int searchByNameTotal(@Param("regionId") int regionId,
                          @Param("name") String name);
}