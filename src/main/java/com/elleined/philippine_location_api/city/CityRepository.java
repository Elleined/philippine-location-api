package com.elleined.philippine_location_api.city;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {

    @Query("CALL city_get_all(:regionId, :provinceId)")
    List<City> findAll(@Param("regionId") int regionId,
                       @Param("provinceId") int provinceId);

    @Query("CALL city_get_all_paginated(:regionId, :provinceId, :page, :size)")
    List<City> findAll(@Param("regionId") int regionId,
                       @Param("provinceId") int provinceId,
                       @Param("page") int page,
                       @Param("size") int size);

    @Query("CALL city_get_all_total(:regionId, :provinceId)")
    int findAllTotal(@Param("regionId") int regionId,
                     @Param("provinceId") int provinceId);

    @Query("CALL city_search_by_name(:regionId, :provinceId, :name)")
    List<City> searchByName(@Param("regionId") int regionId,
                            @Param("provinceId") int provinceId,
                            @Param("name") String name);

    @Query("CALL city_search_by_name_paginated(:regionId, :provinceId, :name, :page, :size)")
    List<City> searchByName(@Param("regionId") int regionId,
                            @Param("provinceId") int provinceId,
                            @Param("name") String name,
                            @Param("page") int page,
                            @Param("size") int size);

    @Query("CALL city_search_by_name_total(:regionId, :provinceId, :name)")
    int searchByNameTotal(@Param("regionId") int regionId,
                          @Param("provinceId") int provinceId,
                          @Param("name") String name);
}