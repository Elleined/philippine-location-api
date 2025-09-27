package com.elleined.philippine_location_api.city;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<City> findAllBy(Long regionId, Long provinceId, String name, int page, int size) {
        return List.of();
    }

    @Override
    public int findAllByTotal(Long regionId, Long provinceId, String name) {
        return 0;
    }
}
