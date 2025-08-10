package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.province.Province;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CityTest {

    @Test
    void toDTO_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method
        AggregateReference<Province, Long> province = AggregateReference.to(1L);
        City city = new City(1L, "name", province);

        // Stubbing methods

        // Calling the method
        CityDTO cityDTO = assertDoesNotThrow(city::toDTO);

        // Behavior Verifications

        // Assertions
        assertThat(cityDTO).isNotNull();
        assertThat(cityDTO.id()).isEqualTo(city.id());
        assertThat(cityDTO.name()).isEqualTo(city.name());
        assertThat(cityDTO.provinceId()).isEqualTo(city.province().getId());
    }
}