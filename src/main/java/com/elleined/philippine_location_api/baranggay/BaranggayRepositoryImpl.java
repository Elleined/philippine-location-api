package com.elleined.philippine_location_api.baranggay;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class BaranggayRepositoryImpl implements BaranggayRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Baranggay> findAllBy(Long regionId, Long provinceId, Long cityId, String name, int page, int size) {
        return List.of();
    }

    @Override
    public int findAllByTotal(Long regionId, Long provinceId, Long cityId, String name) {
        return 0;
    }
}
