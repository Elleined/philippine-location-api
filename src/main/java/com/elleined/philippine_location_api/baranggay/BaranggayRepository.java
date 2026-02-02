package com.elleined.philippine_location_api.baranggay;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaranggayRepository extends CrudRepository<Baranggay, Long> {

    @Query("CALL baranggay_find_all(:region_id, :province_id, :city_id, :name, :page, :size)")
    List<BaranggayDTO> findAll(@Param("region_id") Long regionId,
                               @Param("province_id") Long provinceId,
                               @Param("city_id") Long cityId,
                               @Param("name") String name,
                               @Param("page") int page,
                               @Param("size") int size);


    @Query("SELECT baranggay_find_all_total(:region_id, :province_id, :city_id, :name)")
    int findAllTotal(@Param("region_id") Long regionId,
                     @Param("province_id") Long provinceId,
                     @Param("city_id") Long cityId,
                     @Param("name") String name);
}