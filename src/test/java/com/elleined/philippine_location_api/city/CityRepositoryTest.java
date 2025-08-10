package com.elleined.philippine_location_api.city;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@DataJdbcTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Container
    @ServiceConnection
    private final static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.39")
            .withReuse(true);

    @Test
    void findAll_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<City> cities = assertDoesNotThrow(() -> cityRepository.findAll(regionId, provinceId));

        List<City> expected = new ArrayList<>(cities);
        expected.sort(Comparator.comparing(City::name));

        // Behavior Verifications

        // Assertions
        assertThat(cities).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceIdValues")
    void findAll_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceIdValues(int regionId, int provinceId) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> cityRepository.findAll(regionId, provinceId));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void findAllPaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<City> cities = assertDoesNotThrow(() -> cityRepository.findAll(regionId, provinceId, page, size));

        List<City> expected = new ArrayList<>(cities);
        expected.sort(Comparator.comparing(City::name));

        // Behavior Verifications

        // Assertions
        assertThat(cities).isEqualTo(expected);
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
        int regionId = 1;
        int provinceId = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> cityRepository.findAll(regionId, provinceId, page, size));

        // Behavior Verifications

        // Assertions
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceIdValues")
    void findAllPaged_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceIdValues(int regionId, int provinceId) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> cityRepository.findAll(regionId, provinceId, page, size));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void findAllTotal_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        int total = assertDoesNotThrow(() -> cityRepository.findAllTotal(regionId, provinceId));

        // Behavior Verifications

        // Assertions
        assertThat(total).isGreaterThanOrEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceIdValues")
    void findAllTotal_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceIdValues(int regionId, int provinceId) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> cityRepository.findAllTotal(regionId, provinceId));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void searchByName_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        String name = "name".toLowerCase();

        // Set up method

        // Stubbing methods

        // Calling the method
        List<City> cities = assertDoesNotThrow(() -> cityRepository.searchByName(regionId, provinceId, name));

        List<City> expected = new ArrayList<>(cities);
        expected.sort(Comparator.comparing(City::name));

        // Behavior Verifications

        // Assertions
        assertThat(cities).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNullAndBlankNameValues")
    void searchByName_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNullAndBlankName(int regionId, int provinceId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> cityRepository.searchByName(regionId, provinceId, name));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void searchByNamePaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        String name = "name".toLowerCase();
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<City> cities = assertDoesNotThrow(() -> cityRepository.searchByName(regionId, provinceId, name, page, size));

        List<City> expected = new ArrayList<>(cities);
        expected.sort(Comparator.comparing(City::name));

        boolean contains = cities.stream()
                .map(City::name)
                .map(String::toLowerCase)
                .allMatch(n -> n.contains(name));

        // Behavior Verifications

        // Assertions
        assertThat(contains).isTrue();
        assertThat(cities).isEqualTo(expected);
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
        int regionId = 1;
        int provinceId = 1;
        String name = "name".toLowerCase();

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> cityRepository.searchByName(regionId, provinceId, name, page, size));

        // Behavior Verifications

        // Assertions
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNullAndBlankNameValues")
    void searchByNamePaged_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNullAndBlankName(int regionId, int provinceId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> cityRepository.searchByName(regionId, provinceId, name, page, size));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void searchByNameTotal_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        String name = "name".toLowerCase();

        // Set up method

        // Stubbing methods

        // Calling the method
        int total = assertDoesNotThrow(() -> cityRepository.searchByNameTotal(regionId, provinceId,  name));

        // Behavior Verifications

        // Assertions
        assertThat(total).isGreaterThanOrEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNullAndBlankNameValues")
    void searchByNameTotal_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNullAndBlankName(int regionId, int provinceId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> cityRepository.searchByNameTotal(regionId, provinceId,  name));

        // Behavior Verifications

        // Assertions
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceIdValues() {
        int regionId = 1;
        int provinceId = 1;

        return Stream.of(

                // Negative region id
                Arguments.of(-1, provinceId),
                Arguments.of(-10, provinceId),

                // Negative province id
                Arguments.of(regionId, -1),
                Arguments.of(regionId, -10)
        );
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceId_AndNullAndBlankNameValues() {
        int regionId = 1;
        int provinceId = 1;
        String name = "name".toLowerCase();

        return Stream.of(
                // Null and blank values
                Arguments.of(regionId, provinceId, null),
                Arguments.of(regionId, provinceId, ""),
                Arguments.of(regionId, provinceId, "  "),

                // Negative region id
                Arguments.of(-1, provinceId, name),
                Arguments.of(-10, provinceId, name),

                // Negative province id
                Arguments.of(regionId, -1, name),
                Arguments.of(regionId, -10, name)
        );
    }
}