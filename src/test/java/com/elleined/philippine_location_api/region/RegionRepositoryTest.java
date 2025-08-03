package com.elleined.philippine_location_api.region;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.UncategorizedSQLException;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Container
    @ServiceConnection
    private final static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.39")
            .withReuse(true);

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
    void findAllPage_ShouldThrowUncategorizedSQLException_ForInvalidPageNumberAndPageSize(int page, int size) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> regionRepository.findAll(page, size));

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

    @ParameterizedTest
    @MethodSource("nullAndBlankNameValues")
    void searchByName_ShouldThrowUncategorizedSQLException_ForNullOrBlankName(String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> regionRepository.searchByName(name));

        // Behavior Verifications

        // Assertions
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
    @MethodSource("nullAndBlankNameValues")
    void searchByNamePaged_ShouldThrowUncategorizedSQLException_ForNullOrBlankName(String name) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 0;
        int size = 10;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> regionRepository.searchByName(name, page, size));

        // Behavior Verifications

        // Assertions
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 10",
            "10, -1"
    })
    void searchByNamePaged_ShouldThrowUncategorizedSQLException_ForInvalidPageNumberAndPageSize(int page, int size) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> regionRepository.searchByName("name", page, size));

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

    @ParameterizedTest
    @MethodSource("nullAndBlankNameValues")
    void searchByNameTotal_ShouldThrowUncategorizedSQLException_ForNullOrBlankName(String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> regionRepository.searchByNameTotal(name));

        // Behavior Verifications

        // Assertions
    }

    private static Stream<Arguments> nullAndBlankNameValues() {
        return Stream.of(
                Arguments.of((String) null),
                Arguments.of(""),
                Arguments.of("  ")
        );
    }
}