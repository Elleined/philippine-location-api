package com.elleined.philippine_location_api.region;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class RegionTest {

    @Test
    void toDTO_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        Region region = new Region(1L, "name", "descrption");

        // Set up method

        // Stubbing methods

        // Calling the method
        RegionDTO regionDTO = assertDoesNotThrow(region::toDTO);

        // Behavior Verifications

        // Assertions
        assertThat(regionDTO).isNotNull();
        assertThat(regionDTO.id()).isEqualTo(region.id());
        assertThat(regionDTO.name()).isEqualTo(region.name());
        assertThat(regionDTO.description()).isEqualTo(region.description());
    }
}