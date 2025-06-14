package com.elleined.philippine_location_api.region;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.elleined.philippine_location_api.paging.Page;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@DataJdbcTest
@TestPropertySource(locations = "classpath:.env.test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    void findAll_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        List<Region> regions = assertDoesNotThrow(() -> regionRepository.findAll());

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertFalse(regions.isEmpty());
    }

    @Test
    void findAllPage_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int size = 10;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Region> regions = assertDoesNotThrow(() -> regionRepository.findAll(0, size));

        // Behavior Verifications

        // Assertions
        assertEquals(size, regions.size());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 10",
            "10, -1"
    })
    void findAllPage_ShouldThrowBadSqlGrammarException_ForInvalidPageNumberAndPageSize(int page, int size) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrows(BadSqlGrammarException.class, () -> regionRepository.findAll(page, size));

        // Behavior Verifications

        // Assertions

    }

    @Test
    void findAllTotal_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        int total = assertDoesNotThrow(() -> regionRepository.findAllTotal());

        // Behavior Verifications

        // Assertions
        assertTrue(total > 0);
    }

    @Test
    void searchByName_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        String name = "name".toLowerCase();

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Region> regions = assertDoesNotThrow(() -> regionRepository.searchByName(name));

        boolean contains = regions.stream()
                .map(Region::name)
                .map(String::toLowerCase)
                .allMatch(n -> n.contains(name));

        // Behavior Verifications

        // Assertions
        assertTrue(contains);
    }

    @Test
    void searchByNamePaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        String name = "region".toLowerCase();
        int size = 10;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Region> regions = assertDoesNotThrow(() -> regionRepository.searchByName(name, 0, size));

        boolean contains = regions.stream()
                .map(Region::name)
                .map(String::toLowerCase)
                .allMatch(n -> n.contains(name));

        // Behavior Verifications

        // Assertions
        assertTrue(contains);
        assertEquals(size, regions.size());
    }

    @Test
    void searchByNameTotal_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        String name = "region".toLowerCase();

        // Set up method

        // Stubbing methods

        // Calling the method
        int total = assertDoesNotThrow(() -> regionRepository.searchByNameTotal(name));

        // Behavior Verifications

        // Assertions
        assertTrue(total > 0);
    }
}