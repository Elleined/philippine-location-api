package com.elleined.philippine_location_api.baranggay;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaranggayRepository extends CrudRepository<Baranggay, Integer> {

    @Query("""
        SELECT b.*
        FROM baranggay b
        JOIN city c ON c.id = b.city_id
        JOIN province p ON p.id = c.province_id
        JOIN region r ON r.id = p.region_id
        WHERE r.id = :regionId
        AND p.id = :provinceId
        AND c.id = :cityId
        ORDER BY b.name
        """)
    List<Baranggay> findAll(@Param("regionId") int regionId,
                            @Param("provinceId") int provinceId,
                            @Param("cityId") int cityId);

    @Query("""
        SELECT b.*
        FROM baranggay b
        JOIN city c ON c.id = b.city_id
        JOIN province p ON p.id = c.province_id
        JOIN region r ON r.id = p.region_id
        WHERE r.id = :regionId
        AND p.id = :provinceId
        AND c.id = :cityId
        ORDER BY b.name
        LIMIT :size
        OFFSET :page
        """)
    List<Baranggay> findAll(@Param("regionId") int regionId,
                            @Param("provinceId") int provinceId,
                            @Param("cityId") int cityId,
                            @Param("page") int page,
                            @Param("size") int size);

    @Query("""
        SELECT COUNT(*)
        FROM baranggay b
        JOIN city c ON c.id = b.city_id
        JOIN province p ON p.id = c.province_id
        JOIN region r ON r.id = p.region_id
        WHERE r.id = :regionId
        AND p.id = :provinceId
        AND c.id = :cityId
        """)
    int findAllTotal(@Param("regionId") int regionId,
                     @Param("provinceId") int provinceId,
                     @Param("cityId") int cityId);

    @Query("""
        SELECT b.*
        FROM baranggay b
        JOIN city c ON c.id = b.city_id
        JOIN province p ON p.id = c.province_id
        JOIN region r ON r.id = p.region_id
        WHERE r.id = :regionId
        AND p.id = :provinceId
        AND c.id = :cityId
        AND b.name LIKE CONCAT('%', :name, '%')
        ORDER BY b.name
        """)
    List<Baranggay> searchByName(@Param("regionId") int regionId,
                                 @Param("provinceId") int provinceId,
                                 @Param("cityId") int cityId,
                                 @Param("name") String name);

    @Query("""
        SELECT b.*
        FROM baranggay b
        JOIN city c ON c.id = b.city_id
        JOIN province p ON p.id = c.province_id
        JOIN region r ON r.id = p.region_id
        WHERE r.id = :regionId
        AND p.id = :provinceId
        AND c.id = :cityId
        AND b.name LIKE CONCAT('%', :name, '%')
        ORDER BY b.name
        LIMIT :size
        OFFSET :page
        """)
    List<Baranggay> searchByName(@Param("regionId") int regionId,
                                 @Param("provinceId") int provinceId,
                                 @Param("cityId") int cityId,
                                 @Param("name") String name,
                                 @Param("page") int page,
                                 @Param("size") int size);

    @Query("""
        SELECT COUNT(*)
        FROM baranggay b
        JOIN city c ON c.id = b.city_id
        JOIN province p ON p.id = c.province_id
        JOIN region r ON r.id = p.region_id
        WHERE r.id = :regionId
        AND p.id = :provinceId
        AND c.id = :cityId
        AND b.name LIKE CONCAT('%', :name, '%')
        """)
    int searchByNameTotal(@Param("regionId") int regionId,
                          @Param("provinceId") int provinceId,
                          @Param("cityId") int cityId,
                          @Param("name") String name);
}