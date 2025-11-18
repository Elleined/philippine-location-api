package com.elleined.philippine_location_api.province;

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
public class ProvinceRepositoryImpl implements ProvinceRepository {
    private static final String FIND_ALL_BY_KEY = "findAllByKey";
    private final SimpleJdbcCall findAllBy;
    private final SimpleJdbcCall findAllByTotal;

    private static final Logger log = LoggerFactory.getLogger(ProvinceRepositoryImpl.class);

    public ProvinceRepositoryImpl(@Autowired JdbcTemplate jdbcTemplate) {
        final RowMapper<Province> rowMapper = (resultSet, rowNumber) -> new Province(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getLong(Region.FK_ID));

        this.findAllBy = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("province_find_all_by")
                .returningResultSet(FIND_ALL_BY_KEY, rowMapper);
        this.findAllByTotal = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("province_find_all_by_total")
                .withReturnValue();
    }

    @Override
    public List<Province> findAll(Long regionId, String name, int page, int size) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(Region.FK_ID, regionId, Types.BIGINT)
                .addValue("name", name, Types.VARCHAR)
                .addValue("page", page, Types.INTEGER)
                .addValue("size", size, Types.INTEGER);

        @SuppressWarnings("unchecked")
        List<Province> provinces = (List<Province>) findAllBy.execute(parameters)
                .get(FIND_ALL_BY_KEY);

        log.debug("Provinces: {}", provinces);
        return Objects.requireNonNullElse(provinces, List.of());
    }

    @Override
    public Integer findAllTotal(Long regionId, String name) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(Region.FK_ID, regionId, Types.BIGINT)
                .addValue("name", name, Types.VARCHAR);

        Integer totalElements = findAllByTotal.executeFunction(Integer.class, parameters);
        log.debug("Provinces total elements: {}", totalElements);
        return totalElements;
    }
}
