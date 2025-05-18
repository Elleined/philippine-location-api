package com.elleined.philippine_location_api.city;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {

    @Query("""
            SELECT c.*
            FROM city c
            JOIN province p ON p.id = c.province_id
            JOIN region r ON r.id = p.region_id
            WHERE r.id = :regionId
            AND p.id = :provinceId
            ORDER BY c.name
            """)
    List<City> findAll(@Param("regionId") int regionId,
                       @Param("provinceId") int provinceId);

    @Query("""
            SELECT c.*
            FROM city c
            JOIN province p ON p.id = c.province_id
            JOIN region r ON r.id = p.region_id
            WHERE r.id = :regionId
            AND p.id = :provinceId
            ORDER BY c.name
            LIMIT :size
            OFFSET :page
            """)
    List<City> findAll(@Param("regionId") int regionId,
                       @Param("provinceId") int provinceId,
                       @Param("page") int page,
                       @Param("size") int size);

    @Query("""
            SELECT COUNT(*)
            FROM city c
            JOIN province p ON p.id = c.province_id
            JOIN region r ON r.id = p.region_id
            WHERE r.id = :regionId
            AND p.id = :provinceId
            """)
    int findAllTotal(@Param("regionId") int regionId,
                     @Param("provinceId") int provinceId);

    @Query("""
        SELECT c.*
        FROM city c
        JOIN province p ON p.id = c.province_id
        JOIN region r ON r.id = p.region_id
        WHERE r.id = :regionId
        AND p.id = :provinceId
        AND c.name LIKE CONCAT('%', :name, '%')
        ORDER BY c.name
        """)
    List<City> searchByName(@Param("regionId") int regionId,
                            @Param("provinceId") int provinceId,
                            @Param("name") String name);

    @Query("""
        SELECT c.*
        FROM city c
        JOIN province p ON p.id = c.province_id
        JOIN region r ON r.id = p.region_id
        WHERE r.id = :regionId
        AND p.id = :provinceId
        AND c.name LIKE CONCAT('%', :name, '%')
        ORDER BY c.name
        LIMIT :size
        OFFSET :page
        """)
    List<City> searchByName(@Param("regionId") int regionId,
                            @Param("provinceId") int provinceId,
                            @Param("name") String name,
                            @Param("page") int page,
                            @Param("size") int size);

    @Query("""
        SELECT COUNT(*)
        FROM city c
        JOIN province p ON p.id = c.province_id
        JOIN region r ON r.id = p.region_id
        WHERE r.id = :regionId
        AND p.id = :provinceId
        AND c.name LIKE CONCAT('%', :name, '%')
        """)
    int searchByNameTotal(@Param("regionId") int regionId,
                          @Param("provinceId") int provinceId,
                          @Param("name") String name);
}