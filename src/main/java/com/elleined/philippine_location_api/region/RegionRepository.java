package com.elleined.philippine_location_api.region;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Integer> {

    @Query("""
        SELECT *
        FROM region
        ORDER BY name
        """)
    List<Region> findAll();

    @Query("""
        SELECT *
        FROM region
        ORDER BY name
        LIMIT :size
        OFFSET :page
        """)
    List<Region> findAll(@Param("page") int page,
                         @Param("size") int size);

    @Query("""
        SELECT COUNT(*)
        FROM region
        """)
    int findAllTotal();

    @Query("""
        SELECT *
        FROM region
        WHERE name
        LIKE CONCAT('%', :name, '%')
        ORDER BY name
        """)
    List<Region> searchByName(@Param("name") String name);

    @Query("""
        SELECT *
        FROM region
        WHERE name
        LIKE CONCAT('%', :name, '%')
        ORDER BY name
        LIMIT :size
        OFFSET :page
        """)
    List<Region> searchByName(@Param("name") String name,
                              @Param("page") int page,
                              @Param("size") int size);

    @Query("""
        SELECT COUNT(*)
        FROM region
        WHERE name
        LIKE CONCAT('%', :name, '%')
        """)
    int searchByNameTotal(@Param("name") String name);
}