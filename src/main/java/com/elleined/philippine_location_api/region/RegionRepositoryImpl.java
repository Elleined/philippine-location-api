package com.elleined.philippine_location_api.region;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class RegionRepositoryImpl implements RegionRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Region> rowMapper = (resultSet, rowNumber) -> new Region(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("description"));

    @Override
    public List<Region> findAllBy(String name,
                                  int page,
                                  int size) {

        String key = "key";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("page", page)
                .addValue("size", size);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("barangay_find_all_by")
                .withReturnValue()
                .returningResultSet(key, rowMapper)
                .execute(parameters);

        @SuppressWarnings("unchecked")
        List<Region> regions = (List<Region>) Objects.requireNonNullElse(result.get(key), List.of());

        return regions;
    }

    @Override
    public int findAllByTotal(String name) {
        return 0;
    }
}
