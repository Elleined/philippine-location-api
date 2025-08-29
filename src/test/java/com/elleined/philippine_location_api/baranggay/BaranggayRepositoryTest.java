package com.elleined.philippine_location_api.baranggay;

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
class BaranggayRepositoryTest {

    @Autowired
    private BaranggayRepository baranggayRepository;

    @Container
    @ServiceConnection
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.39")
            .withReuse(true);

    @Test
    void findAll_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Baranggay> baranggays = assertDoesNotThrow(() -> baranggayRepository.findAll(regionId, provinceId, cityId));

        List<Baranggay> expected = new ArrayList<>(baranggays);
        expected.sort(Comparator.comparing(Baranggay::name));

        // Behavior Verifications

        // Assertions
        assertThat(baranggays).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityIdValues")
    void findAll_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId(int regionId, int provinceId, int cityId) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> baranggayRepository.findAll(regionId, provinceId, cityId));

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
        int cityId = 1;
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Baranggay> baranggays = assertDoesNotThrow(() -> baranggayRepository.findAll(regionId, provinceId, cityId, page, size));

        List<Baranggay> expected = new ArrayList<>(baranggays);
        expected.sort(Comparator.comparing(Baranggay::name));

        // Behavior Verifications

        // Assertions
        assertThat(baranggays).isEqualTo(expected);
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
        int cityId = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> baranggayRepository.findAll(regionId, provinceId, cityId, page, size));

        // Behavior Verifications

        // Assertions
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityIdValues")
    void findAllPaged_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId(int regionId, int provinceId, int cityId) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> baranggayRepository.findAll(regionId, provinceId, cityId, page, size));

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
        int cityId = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        int total = assertDoesNotThrow(() -> baranggayRepository.findAllTotal(regionId, provinceId, cityId));

        // Behavior Verifications

        // Assertions
        assertThat(total).isGreaterThanOrEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityIdValues")
    void findAllTotal_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId(int regionId, int provinceId, int cityId) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> baranggayRepository.findAllTotal(regionId, provinceId, cityId));

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
        int cityId = 1;
        String name = "name".toLowerCase(Locale.ROOT);

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Baranggay> baranggays = assertDoesNotThrow(() -> baranggayRepository.searchByName(regionId, provinceId, cityId, name));

        List<Baranggay> expected = new ArrayList<>(baranggays);
        expected.sort(Comparator.comparing(Baranggay::name));

        boolean contains = baranggays.stream()
                .map(Baranggay::name)
                .map(String::toLowerCase)
                .allMatch(n -> n.contains(name));

        // Behavior Verifications

        // Assertions
        assertThat(contains).isTrue();
        assertThat(baranggays).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankNameValues")
    void searchByName_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankName(int regionId, int provinceId, int cityId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> baranggayRepository.searchByName(regionId, provinceId, cityId, name));

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
        int cityId = 1;
        String name = "name".toLowerCase(Locale.ROOT);
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        List<Baranggay> baranggays = assertDoesNotThrow(() -> baranggayRepository.searchByName(regionId, provinceId, cityId, name, page, size));

        List<Baranggay> expected = new ArrayList<>(baranggays);
        expected.sort(Comparator.comparing(Baranggay::name));

        boolean contains = baranggays.stream()
                .map(Baranggay::name)
                .map(String::toLowerCase)
                .allMatch(n -> n.contains(name));

        // Behavior Verifications

        // Assertions
        assertThat(contains).isTrue();
        assertThat(baranggays).isEqualTo(expected);
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
        int cityId = 1;
        String name = "name".toLowerCase(Locale.ROOT);

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> baranggayRepository.searchByName(regionId, provinceId, cityId, name, page, size));

        // Behavior Verifications

        // Assertions
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankNameValues")
    void searchByNamePaged_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankName(int regionId, int provinceId, int cityId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> baranggayRepository.searchByName(regionId, provinceId, cityId, name, page, size));

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
        int cityId = 1;
        String name = "name".toLowerCase(Locale.ROOT);

        // Set up method

        // Stubbing methods

        // Calling the method
        int total = assertDoesNotThrow(() -> baranggayRepository.searchByNameTotal(regionId, provinceId, cityId, name));

        // Behavior Verifications

        // Assertions
        assertThat(total).isGreaterThanOrEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankNameValues")
    void searchByNameTotal_ShouldThrowUncategorizedSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankName(int regionId, int provinceId, int cityId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(UncategorizedSQLException.class, () -> baranggayRepository.searchByNameTotal(regionId, provinceId, cityId, name));

        // Behavior Verifications

        // Assertions
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceId_AndNegativeCityIdValues() {
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;

        return Stream.of(
                // Negative region id
                Arguments.of(-1, provinceId, cityId),
                Arguments.of(-10, provinceId, cityId),

                // Negative province id
                Arguments.of(regionId, -1, cityId),
                Arguments.of(regionId, -10, cityId),

                // Negative city id
                Arguments.of(regionId, provinceId, -1),
                Arguments.of(regionId, provinceId, -10)
        );
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankNameValues() {
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;
        String name = "name".toLowerCase(Locale.ROOT);

        return Stream.of(
                // Null and blank values
                Arguments.of(regionId, provinceId, cityId, null),
                Arguments.of(regionId, provinceId, cityId, ""),
                Arguments.of(regionId, provinceId, cityId, "  "),


                // Negative region id
                Arguments.of(-1, provinceId, cityId, name),
                Arguments.of(-10, provinceId, cityId, name),

                // Negative province id
                Arguments.of(regionId, -1, cityId, name),
                Arguments.of(regionId, -10, cityId, name),

                // Negative city id
                Arguments.of(regionId, provinceId, -1, name),
                Arguments.of(regionId, provinceId, -10, name)
        );
    }
}