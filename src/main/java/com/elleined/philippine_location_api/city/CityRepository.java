package com.elleined.philippine_location_api.city;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {

    @Query("CALL city_find_all_by(:regionId, :provinceId, :name, :page, :size)")
    List<City> findAllBy(@Param("regionId") int regionId,
                         @Param("provinceId") int provinceId,
                         @Param("name") String name,
                         @Param("page") int page,
                         @Param("size") int size);

    @Query("CALL city_find_all_by_total(:regionId, :provinceId, :name)")
    int findAllByTotal(@Param("regionId") int regionId,
                       @Param("provinceId") int provinceId,
                       @Param("name") String name);
}