package com.elleined.philippine_location_api.city;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {

    @Query("CALL city_find_all(:region_id, :province_id, :name, :page, :size)")
    List<CityDTO> findAll(@Param("region_id") Long regionId,
                          @Param("province_id") Long provinceId,
                          @Param("name") String name,
                          @Param("page") int page,
                          @Param("size") int size);


    @Query("SELECT city_find_all_total(:region_id, :province_id, :name)")
    int findAllTotal(@Param("region_id") Long regionId,
                     @Param("province_id") Long provinceId,
                     @Param("name") String name);
}