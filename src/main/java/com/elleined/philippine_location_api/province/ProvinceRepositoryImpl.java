package com.elleined.philippine_location_api.province;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProvinceRepositoryImpl implements ProvinceRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Province> rowMapper = (resultSet, rowNumber) -> new Province(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getLong("region_id"));

    @Override
    public List<Province> findAllBy(Long regionId, String name, int page, int size) {
        return List.of();
    }

    @Override
    public int findAllByTotal(Long regionId, String name) {
        return 0;
    }
}
