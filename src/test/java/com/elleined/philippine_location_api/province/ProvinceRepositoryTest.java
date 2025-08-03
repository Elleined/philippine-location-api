package com.elleined.philippine_location_api.province;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
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
class ProvinceRepositoryTest {

    @Autowired
    private ProvinceRepository provinceRepository;

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

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Province> provinces = assertDoesNotThrow(() -> provinceRepository.findAll(regionId));

        List<Province> expected = new ArrayList<>(provinces);
        expected.sort(Comparator.comparing(Province::name));

        // Behavior Verifications

        // Assertions
        assertEquals(expected, provinces);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void findAll_ShouldThrowUncategorizedSQLException_ForNegativeRegionId(int regionId) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> provinceRepository.findAll(regionId));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void findAllPaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int page = 0;
        int size = 10;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Province> provinces = assertDoesNotThrow(() -> provinceRepository.findAll(regionId, page, size));
        provinces.forEach(System.out::println);

        List<Province> expected = new ArrayList<>(provinces);
        expected.sort(Comparator.comparing(Province::name));

        // Behavior Verifications

        // Assertions
        assertEquals(expected, provinces);
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

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> provinceRepository.findAll(regionId, page, size));

        // Behavior Verifications

        // Assertions
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void findAllPaged_ShouldThrowUncategorizedSQLException_ForNegativeRegionId(int regionId) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 0;
        int size = 10;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> provinceRepository.findAll(regionId, page, size));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void findAllTotal_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        int total = assertDoesNotThrow(() -> provinceRepository.findAllTotal(regionId));

        // Behavior Verifications

        // Assertions
        assertTrue(total >= 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void findAllTotal_ShouldThrowUncategorizedSQLException_ForNegativeRegionId(int regionId) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> provinceRepository.findAllTotal(regionId));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void searchByName_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        String name = "name".toLowerCase();

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Province> provinces = assertDoesNotThrow(() -> provinceRepository.searchByName(regionId, name));

        List<Province> expected = new ArrayList<>(provinces);
        expected.sort(Comparator.comparing(Province::name));

        boolean contains = provinces.stream()
                .map(Province::name)
                .map(String::toLowerCase)
                .allMatch(n -> n.contains(name));


        // Behavior Verifications

        // Assertions
        assertTrue(contains);
        assertEquals(expected, provinces);
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNullAndBlankNameValues")
    void searchByName_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_And_NullAndBlankName(int regionId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> provinceRepository.searchByName(regionId, name));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void searchByNamePaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        String name = "name".toLowerCase();
        int page = 0;
        int size = 10;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Province> provinces = assertDoesNotThrow(() -> provinceRepository.searchByName(regionId, name, page, size));

        List<Province> expected = new ArrayList<>(provinces);
        expected.sort(Comparator.comparing(Province::name));

        boolean contains = provinces.stream()
                .map(Province::name)
                .map(String::toLowerCase)
                .allMatch(n -> n.contains(name));


        // Behavior Verifications

        // Assertions
        assertTrue(contains);
        assertEquals(expected, provinces);
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
        String name = "name".toLowerCase();

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> provinceRepository.searchByName(regionId, name, page, size));

        // Behavior Verifications

        // Assertions
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNullAndBlankNameValues")
    void searchByNamePaged_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_And_NullAndBlankName(int regionId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> provinceRepository.searchByName(regionId, name, page, size));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void searchByNameTotal_HappyPath() {
        // Pre defined values

        // Expected Value
        int regionId = 1;
        String name = "name".toLowerCase();

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        int total = assertDoesNotThrow(() -> provinceRepository.searchByNameTotal(regionId, name));

        // Behavior Verifications

        // Assertions
        assertTrue(total >= 0);
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNullAndBlankNameValues")
    void searchByNameTotal_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_And_NullAndBlankName(int regionId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> provinceRepository.searchByNameTotal(regionId, name));

        // Behavior Verifications

        // Assertions
    }

    private static Stream<Arguments> negativeRegionId_AndNullAndBlankNameValues() {
        int regionId = 1;
        String name = "name".toLowerCase();

        return Stream.of(
                // Null and blank values
                Arguments.of(regionId, null),
                Arguments.of(regionId, ""),
                Arguments.of(regionId, "  "),

                // Negative region id
                Arguments.of(-1, name),
                Arguments.of(-10, name)
        );
    }
}