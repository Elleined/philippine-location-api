package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.province.Province;
import com.elleined.philippine_location_api.region.Region;
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
public class CityRepositoryImpl implements CityRepository {
    private static final String FIND_ALL_BY_KEY_KEY = "findAllByKey";
    private final SimpleJdbcCall findAllBy;
    private final SimpleJdbcCall findAllByTotal;

    private static final Logger log = LoggerFactory.getLogger(CityRepositoryImpl.class);

    public CityRepositoryImpl(@Autowired JdbcTemplate jdbcTemplate) {
        final RowMapper<City> rowMapper = (resultSet, rowNumber) -> new City(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getLong(Region.FK_ID),
                resultSet.getLong(Province.FK_ID));

        this.findAllBy = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("city_find_all_by")
                .returningResultSet(FIND_ALL_BY_KEY_KEY, rowMapper);
        this.findAllByTotal = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("city_find_all_by_total")
                .withReturnValue();
    }

    @Override
    public List<City> findAll(Long regionId, Long provinceId, String name, int page, int size) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(Region.FK_ID, regionId, Types.BIGINT)
                .addValue(Province.FK_ID, provinceId, Types.BIGINT)
                .addValue("name", name, Types.VARCHAR)
                .addValue("page", page, Types.INTEGER)
                .addValue("size", size, Types.INTEGER);

        @SuppressWarnings("unchecked")
        List<City> cities = (List<City>) findAllBy.execute(parameters)
                .get(FIND_ALL_BY_KEY_KEY);

        log.debug("Cities: {}", cities);
        return Objects.requireNonNullElse(cities, List.of());
    }

    @Override
    public Integer findAllTotal(Long regionId, Long provinceId, String name) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(Region.FK_ID, regionId, Types.BIGINT)
                .addValue(Province.FK_ID, provinceId, Types.BIGINT)
                .addValue("name", name, Types.VARCHAR);

        Integer totalElements = findAllByTotal.executeFunction(Integer.class, parameters);
        log.debug("Cities total elements: {}", totalElements);
        return totalElements;
    }
}
