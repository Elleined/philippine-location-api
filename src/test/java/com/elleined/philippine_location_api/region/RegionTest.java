package com.elleined.philippine_location_api.region;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
        assertNotNull(regionDTO);
        assertEquals(regionDTO.id(), region.id());
        assertEquals(regionDTO.name(), region.name());
        assertEquals(regionDTO.description(), region.description());
    }
}