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
import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@DataJdbcTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Container
    @ServiceConnection
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.4.6")
            .withReuse(true);

    @Test
    void findAll_HappyPath() {
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
        assertThat(regions).isEqualTo(expected);
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
        assertThat(regions).isEqualTo(expected);
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
        assertThat(total).isGreaterThanOrEqualTo(0);
    }

    @Test
    void findAllBy_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        String name = "name".toLowerCase(Locale.ROOT);

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Region> regions = assertDoesNotThrow(() -> regionRepository.findAllBy(name));

        List<Region> expected = new ArrayList<>(regions);
        expected.sort(Comparator.comparing(Region::name));

        boolean contains = regions.stream()
                .map(Region::name)
                .map(String::toLowerCase)
                .allMatch(n -> n.contains(name));

        // Behavior Verifications

        // Assertions
        assertThat(contains).isTrue();
        assertThat(regions).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("nullAndBlankNameValues")
    void findAllByName_ShouldThrowUncategorizedSQLException_ForNullOrBlank(String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> regionRepository.findAllBy(name));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void findAllByPaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        String name = "name".toLowerCase(Locale.ROOT);
        int page = 0;
        int size = 10;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Region> regions = assertDoesNotThrow(() -> regionRepository.findAllBy(name, page, size));

        List<Region> expected = new ArrayList<>(regions);
        expected.sort(Comparator.comparing(Region::name));

        boolean contains = regions.stream()
                .map(Region::name)
                .map(String::toLowerCase)
                .allMatch(n -> n.contains(name));

        // Behavior Verifications

        // Assertions
        assertThat(contains).isTrue();
        assertThat(regions).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("nullAndBlankNameValues")
    void findAllByNamePaged_ShouldThrowUncategorizedSQLException_ForNullOrBlank(String name) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 0;
        int size = 10;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> regionRepository.findAllBy(name, page, size));

        // Behavior Verifications

        // Assertions
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 10",
            "10, -1"
    })
    void findAllByPaged_ShouldThrowUncategorizedSQLException_ForInvalidPageNumberAndPageSize(int page, int size) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> regionRepository.findAllBy("name", page, size));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void findAllByTotal_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        String name = "name".toLowerCase(Locale.ROOT);

        // Set up method

        // Stubbing methods

        // Calling the method
        int total = assertDoesNotThrow(() -> regionRepository.findAllByTotal(name));

        // Behavior Verifications

        // Assertions
        assertThat(total).isGreaterThanOrEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("nullAndBlankNameValues")
    void findAllByNameTotal_ShouldThrowUncategorizedSQLException_ForNullOrBlank(String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> regionRepository.findAllByTotal(name));

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