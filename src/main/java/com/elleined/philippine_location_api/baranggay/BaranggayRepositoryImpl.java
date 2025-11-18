package com.elleined.philippine_location_api.baranggay;

import com.elleined.philippine_location_api.city.City;
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
public class BaranggayRepositoryImpl implements BaranggayRepository {
    private static final String FIND_ALL_BY_KEY = "findAllByKey";
    private final SimpleJdbcCall findAllBy;
    private final SimpleJdbcCall findAllByTotal;

    private static final Logger log = LoggerFactory.getLogger(BaranggayRepositoryImpl.class);

    public BaranggayRepositoryImpl(@Autowired JdbcTemplate jdbcTemplate) {
        final RowMapper<Baranggay> rowMapper = (resultSet, rowNumber) -> new Baranggay(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getLong(Region.FK_ID),
                resultSet.getLong(Province.FK_ID),
                resultSet.getLong(City.FK_ID));

        this.findAllBy = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("baranggay_find_all_by")
                .returningResultSet(FIND_ALL_BY_KEY, rowMapper);
        this.findAllByTotal = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("baranggay_find_all_by_total")
                .withReturnValue();
    }

    @Override
    public List<Baranggay> findAll(Long regionId, Long provinceId, Long cityId, String name, int page, int size) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(Region.FK_ID, regionId, Types.BIGINT)
                .addValue(Province.FK_ID, provinceId, Types.BIGINT)
                .addValue(City.FK_ID, cityId, Types.BIGINT)
                .addValue("name", name, Types.VARCHAR)
                .addValue("page", page, Types.INTEGER)
                .addValue("size", size, Types.INTEGER);

        @SuppressWarnings("unchecked")
        List<Baranggay> baranggays = (List<Baranggay>) findAllBy.execute(parameters)
                .get(FIND_ALL_BY_KEY);

        log.debug("Baranggay: {}", baranggays);
        return Objects.requireNonNullElse(baranggays, List.of());
    }

    @Override
    public Integer findAllTotal(Long regionId, Long provinceId, Long cityId, String name) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue(Region.FK_ID, regionId, Types.BIGINT)
                .addValue(Province.FK_ID, provinceId, Types.BIGINT)
                .addValue(City.FK_ID, cityId, Types.BIGINT)
                .addValue("name", name, Types.VARCHAR);

        Integer totalElements = findAllByTotal.executeFunction(Integer.class, parameters);
        log.debug("Cities total elements: {}", totalElements);
        return totalElements;
    }
}
