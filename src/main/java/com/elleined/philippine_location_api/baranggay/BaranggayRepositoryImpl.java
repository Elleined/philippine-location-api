package com.elleined.philippine_location_api.baranggay;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BaranggayRepositoryImpl implements BaranggayRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Baranggay> rowMapper = (resultSet, rowNumber) -> new Baranggay(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getLong("city_id"));

    public BaranggayRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Baranggay> findAllBy(Long regionId, Long provinceId, Long cityId, String name, int page, int size) {
        return List.of();
    }

    @Override
    public int findAllByTotal(Long regionId, Long provinceId, Long cityId, String name) {
        return 0;
    }
}
