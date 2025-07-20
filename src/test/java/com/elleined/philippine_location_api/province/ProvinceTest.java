package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.region.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProvinceTest {

    @Test
    void toDTO_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        AggregateReference<Region, Long> region = AggregateReference.to(1L);
        Province province = new Province(1L, "name", region);

        // Set up method

        // Stubbing methods

        // Calling the method
        ProvinceDTO provinceDTO = assertDoesNotThrow(province::toDTO);

        // Behavior Verifications

        // Assertions
        assertNotNull(provinceDTO);
        assertEquals(provinceDTO.id(), province.id());
        assertEquals(provinceDTO.name(), province.name());
        assertEquals(provinceDTO.regionId(), province.region().getId());
    }
}