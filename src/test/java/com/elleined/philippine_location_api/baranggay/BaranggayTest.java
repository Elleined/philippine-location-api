package com.elleined.philippine_location_api.baranggay;

import com.elleined.philippine_location_api.city.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class BaranggayTest {

    @Test
    void toDTO_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        AggregateReference<City, Long> city = AggregateReference.to(1L);
        Baranggay baranggay = new Baranggay(1L, "name", city);

        // Set up method

        // Stubbing methods

        // Calling the method
        BaranggayDTO baranggayDTO = assertDoesNotThrow(baranggay::toDTO);

        // Behavior Verifications

        // Assertions
        assertThat(baranggayDTO).isNotNull();
        assertThat(baranggayDTO.id()).isEqualTo(baranggay.id());
        assertThat(baranggayDTO.name()).isEqualTo(baranggay.name());
        assertThat(baranggayDTO.cityId()).isEqualTo(baranggay.city().getId());
    }
}