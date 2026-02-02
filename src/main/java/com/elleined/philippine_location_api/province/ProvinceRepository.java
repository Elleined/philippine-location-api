package com.elleined.philippine_location_api.province;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceRepository extends CrudRepository<Province, Long> {

    @Query("CALL province_find_all(:region_id, :name, :page, :size)")
    List<ProvinceDTO> findAll(@Param("region_id") Long regionId,
                              @Param("name") String name,
                              @Param("page") int page,
                              @Param("size") int size);


    @Query("SELECT province_find_all_total(:region_id, :name)")
    int findAllTotal(@Param("region_id") Long regionId, @Param("name") String name);
}