package com.elleined.philippine_location_api.paging;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PageRequestTest {

    @Test
    void of_HappyPath() {
        // Pre defined values

        // Expected Value
        int page = 1;
        int size = 10;

        // Mock data
        PageRequest request = assertDoesNotThrow(() -> PageRequest.of(page, size));

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertEquals(page, request.page());
        assertEquals(size, request.size());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 10",
            "10, -1",
            "0, 10",
            "10, 0"
    })
    void of_ShouldThrowIllegalArgumentException_ForNegativePage_AndNegativeSize(int page, int size) {
        // Pre defined values

        // Expected Value

        // Mock data
        assertThrows(IllegalArgumentException.class, () -> PageRequest.of(page, size));

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
    }

    @Test
    void getOffset_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 10;
        int offset = 0;

        // Mock data
        PageRequest request = assertDoesNotThrow(() -> PageRequest.of(page, size));

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertEquals(offset, request.getOffset());
    }
}