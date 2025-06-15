package com.elleined.philippine_location_api.baranggay;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.context.TestPropertySource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@TestPropertySource(locations = "classpath:.env.test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BaranggayRepositoryTest {

    @Autowired
    private BaranggayRepository baranggayRepository;

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
        assertEquals(expected, baranggays);
    }

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityIdValues")
    void findAll_ShouldThrowSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId(int regionId, int provinceId, int cityId) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(BadSqlGrammarException.class, () -> baranggayRepository.findAll(regionId, provinceId, cityId));

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
        assertEquals(expected, baranggays);
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
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(BadSqlGrammarException.class, () -> baranggayRepository.findAll(regionId, provinceId, cityId, page, size));

        // Behavior Verifications

        // Assertions
    }

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityIdValues")
    void findAllPaged_ShouldThrowSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId(int regionId, int provinceId, int cityId) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(BadSqlGrammarException.class, () -> baranggayRepository.findAll(regionId, provinceId, cityId, page, size));

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
        assertTrue(total >= 0);
    }

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityIdValues")
    void findAllTotal_ShouldThrowSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId(int regionId, int provinceId, int cityId) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(SQLException.class, () -> baranggayRepository.findAllTotal(regionId, provinceId, cityId));

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
        String name = "name".toLowerCase();

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
        assertTrue(contains);
        assertEquals(expected, baranggays);
    }

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankNameValues")
    void searchByName_ShouldThrowSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankName(int regionId, int provinceId, int cityId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(BadSqlGrammarException.class, () -> baranggayRepository.searchByName(regionId, provinceId, cityId, name));

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
        String name = "name".toLowerCase();
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
        assertTrue(contains);
        assertEquals(expected, baranggays);
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
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;
        String name = "name".toLowerCase();

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(BadSqlGrammarException.class, () -> baranggayRepository.searchByName(regionId, provinceId, cityId, name, page, size));

        // Behavior Verifications

        // Assertions
    }

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankNameValues")
    void searchByNamePaged_ShouldThrowSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankName(int regionId, int provinceId, int cityId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(SQLException.class, () -> baranggayRepository.searchByName(regionId, provinceId, cityId, name, page, size));

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
        String name = "name".toLowerCase();

        // Set up method

        // Stubbing methods

        // Calling the method
        int total = assertDoesNotThrow(() -> baranggayRepository.searchByNameTotal(regionId, provinceId, cityId, name));

        // Behavior Verifications

        // Assertions
        assertTrue(total >= 0);
    }

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankNameValues")
    void searchByNameTotal_ShouldThrowSQLException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankName(int regionId, int provinceId, int cityId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(SQLException.class, () -> baranggayRepository.searchByNameTotal(regionId, provinceId, cityId, name));

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
        String name = "name".toLowerCase();

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