package com.elleined.philippine_location_api.region;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class RegionRepositoryImpl implements RegionRepository {
    private static final String FIND_ALL_BY_KEY = "findAllByKey";
    private final SimpleJdbcCall findAllBy;
    private final SimpleJdbcCall findAllByTotal;

    private static final Logger log = LoggerFactory.getLogger(RegionRepositoryImpl.class);

    public RegionRepositoryImpl(@Autowired JdbcTemplate jdbcTemplate) {
        RowMapper<Region> rowMapper = (resultSet, rowNumber) -> new Region(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("description"));

        this.findAllBy = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("region_find_all_by")
                .returningResultSet(FIND_ALL_BY_KEY, rowMapper);
        this.findAllByTotal = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("region_find_all_by_total")
                .withReturnValue();
    }

    @Override
    public List<Region> findAllBy(String name,
                                  int page,
                                  int size) {

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", name, Types.VARCHAR)
                .addValue("page", page, Types.INTEGER)
                .addValue("size", size, Types.INTEGER);

        @SuppressWarnings("unchecked")
        List<Region> regions = (List<Region>) findAllBy.execute(parameters)
                .get(FIND_ALL_BY_KEY);

        log.debug("Regions: {}", regions);
        return Objects.requireNonNullElse(regions, List.of());
    }

    @Override
    public Integer findAllByTotal(String name) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", name, Types.VARCHAR);

        Integer totalElements = findAllByTotal.executeFunction(Integer.class, parameters);
        log.debug("Regions total elements: {}", totalElements);
        return totalElements;
    }
}
