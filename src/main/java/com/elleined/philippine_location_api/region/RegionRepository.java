package com.elleined.philippine_location_api.region;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Long> {

    @Query("CALL region_find_all(:name, :page, :size)")
    List<RegionDTO> findAll(@Param("name") String name,
                            @Param("page") int page,
                            @Param("size") int size);


    @Query("SELECT region_find_all_total(:name)")
    int findAllTotal(@Param("name") String name);
}