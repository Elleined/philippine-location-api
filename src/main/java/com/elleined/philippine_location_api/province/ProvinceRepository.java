package com.elleined.philippine_location_api.province;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceRepository extends CrudRepository<Province, Integer> {

    @Query("CALL province_find_all_by(:regionId, :name, :page, :size)")
    List<Province> findAllBy(@Param("regionId") int regionId,
                             @Param("name") String name,
                             @Param("page") int page,
                             @Param("size") int size);

    @Query("CALL province_find_all_by_total(:regionId, :name)")
    int findAllByTotal(@Param("regionId") int regionId,
                       @Param("name") String name);
}