package com.elleined.philippine_location_api.baranggay;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaranggayRepository extends CrudRepository<Baranggay, Integer> {

    @Query("CALL baranggay_find_all_by(:regionId, :provinceId, :cityId, :name, :page, :size)")
    List<Baranggay> findAllBy(@Param("regionId") int regionId,
                              @Param("provinceId") int provinceId,
                              @Param("cityId") int cityId,
                              @Param("name") String name,
                              @Param("page") int page,
                              @Param("size") int size);

    @Query("CALL baranggay_find_all_by_total(:regionId, :provinceId, :cityId, :name)")
    int findAllByTotal(@Param("regionId") int regionId,
                       @Param("provinceId") int provinceId,
                       @Param("cityId") int cityId,
                       @Param("name") String name);
}