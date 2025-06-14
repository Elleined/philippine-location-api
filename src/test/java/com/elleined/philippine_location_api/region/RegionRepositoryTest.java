package com.elleined.philippine_location_api.region;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        // Calling the method
        List<Region> regions = assertDoesNotThrow(() -> regionRepository.findAll());

        List<Region> expected = new ArrayList<>(regions);
        expected.sort(Comparator.comparing(Region::name));

        // Behavior Verifications

        // Assertions
        assertEquals(expected, regions);
    }

    @Test
    void findAllPage_HappyPath() {
        // Pre defined values

        // Expected Value
        int page = 0;
        int size = 10;

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Region> regions = assertDoesNotThrow(() -> regionRepository.findAll(page, size));

        List<Region> expected = new ArrayList<>(regions);
        expected.sort(Comparator.comparing(Region::name));

        // Behavior Verifications

        // Assertions
        assertEquals(expected, regions);
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
        assertThrowsExactly(BadSqlGrammarException.class, () -> regionRepository.findAll(page, size));

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
        assertTrue(total >= 0);
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

        List<Region> expected = new ArrayList<>(regions);
        expected.sort(Comparator.comparing(Region::name));

        boolean contains = regions.stream()
                .map(Region::name)
                .map(String::toLowerCase)
                .allMatch(n -> n.contains(name));

        // Behavior Verifications

        // Assertions
        assertTrue(contains);
        assertEquals(expected, regions);
    }

    @Test
    void searchByNamePaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        String name = "name".toLowerCase();
        int page = 0;
        int size = 10;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Region> regions = assertDoesNotThrow(() -> regionRepository.searchByName(name, page, size));

        List<Region> expected = new ArrayList<>(regions);
        expected.sort(Comparator.comparing(Region::name));

        boolean contains = regions.stream()
                .map(Region::name)
                .map(String::toLowerCase)
                .allMatch(n -> n.contains(name));

        // Behavior Verifications

        // Assertions
        assertTrue(contains);
        assertEquals(expected, regions);
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 10",
            "10, -1"
    })
    void searchByNamePaged_ShouldThrowBadSqlGrammarException_ForInvalidPageNumberAndPageSize(int page, int size) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(BadSqlGrammarException.class, () -> regionRepository.searchByName("name", page, size));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void searchByNameTotal_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        String name = "name".toLowerCase();

        // Set up method

        // Stubbing methods

        // Calling the method
        int total = assertDoesNotThrow(() -> regionRepository.searchByNameTotal(name));

        // Behavior Verifications

        // Assertions
        assertTrue(total >= 0);
    }
}