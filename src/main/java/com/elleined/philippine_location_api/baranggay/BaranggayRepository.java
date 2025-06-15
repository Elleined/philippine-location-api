package com.elleined.philippine_location_api.baranggay;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaranggayRepository extends CrudRepository<Baranggay, Integer> {

    @Query("CALL baranggay_get_all(:regionId, :provinceId, :cityId)")
    List<Baranggay> findAll(@Param("regionId") int regionId,
                            @Param("provinceId") int provinceId,
                            @Param("cityId") int cityId);

    @Query("CALL baranggay_get_all_paginated(:regionId, :provinceId, :cityId, :page, :size)")
    List<Baranggay> findAll(@Param("regionId") int regionId,
                            @Param("provinceId") int provinceId,
                            @Param("cityId") int cityId,
                            @Param("page") int page,
                            @Param("size") int size);

    @Query("CALL baranggay_get_all_total(:regionId, :provinceId, :cityId)")
    int findAllTotal(@Param("regionId") int regionId,
                     @Param("provinceId") int provinceId,
                     @Param("cityId") int cityId);

    @Query("CALL baranggay_search_by_name(:regionId, :provinceId, :cityId, :name)")
    List<Baranggay> searchByName(@Param("regionId") int regionId,
                                 @Param("provinceId") int provinceId,
                                 @Param("cityId") int cityId,
                                 @Param("name") String name);

    @Query("CALL baranggay_search_by_name_paginated(:regionId, :provinceId, :cityId, :name, :page, :size)")
    List<Baranggay> searchByName(@Param("regionId") int regionId,
                                 @Param("provinceId") int provinceId,
                                 @Param("cityId") int cityId,
                                 @Param("name") String name,
                                 @Param("page") int page,
                                 @Param("size") int size);

    @Query("CALL baranggay_search_by_name_total(:regionId, :provinceId, :cityId, :name)")
    int searchByNameTotal(@Param("regionId") int regionId,
                          @Param("provinceId") int provinceId,
                          @Param("cityId") int cityId,
                          @Param("name") String name);
}