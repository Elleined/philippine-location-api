package com.elleined.philippine_location_api.region;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class RegionRepositoryImpl implements RegionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Region> findAllBy(String name, int page, int size) {
        return List.of();
    }

    @Override
    public int findAllByTotal(String name) {
        return 0;
    }
}
