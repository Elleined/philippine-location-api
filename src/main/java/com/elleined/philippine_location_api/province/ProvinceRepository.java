package com.elleined.philippine_location_api.province;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceRepository extends CrudRepository<Province, Integer> {

    @Query("""
        SELECT *
        FROM province
        WHERE region_id = :regionId
        ORDER BY name
        """)
    List<Province> findAll(@Param("regionId") int regionId);

    @Query("""
        SELECT *
        FROM province
        WHERE region_id = :regionId
        ORDER BY name
        LIMIT :size
        OFFSET :page
        """)
    List<Province> findAll(@Param("regionId") int regionId,
                           @Param("page") int page,
                           @Param("size") int size);

    @Query("""
        SELECT COUNT(*)
        FROM province
        WHERE region_id = :regionId
        """)
    int findAllTotal(@Param("regionId") int regionId);

    @Query("""
        SELECT *
        FROM province
        WHERE region_id = :regionId
        AND name LIKE CONCAT('%', :name, '%')
        ORDER BY name
        """)
    List<Province> searchByName(@Param("regionId") int regionId,
                                @Param("name") String name);

    @Query("""
        SELECT *
        FROM province
        WHERE region_id = :regionId
        AND name LIKE CONCAT('%', :name, '%')
        ORDER BY name
        LIMIT :size
        OFFSET :page
        """)
    List<Province> searchByName(@Param("regionId") int regionId,
                                @Param("name") String name,
                                @Param("page") int page,
                                @Param("size") int size);

    @Query("""
        SELECT COUNT(*)
        FROM province
        WHERE region_id = :regionId
        AND name LIKE CONCAT('%', :name, '%')
        """)
    int searchByNameTotal(@Param("regionId") int regionId,
                          @Param("name") String name);
}