package com.elleined.philippine_location_api.province;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.elleined.philippine_location_api.region.Region;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
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

@DataJdbcTest
@TestPropertySource(locations = "classpath:.env.test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProvinceRepositoryTest {

    @Autowired
    private ProvinceRepository provinceRepository;

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

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void findAll_ShouldThrowSQLException_ForNegativeRegionId(int regionId) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(SQLException.class, () -> provinceRepository.findAll(regionId));

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
    void findAllPage_ShouldThrowBadSqlGrammarException_ForInvalidPageNumberAndPageSize(int page, int size) {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(BadSqlGrammarException.class, () -> provinceRepository.findAll(regionId, page, size));

        // Behavior Verifications

        // Assertions
    }

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void findAllPaged_ShouldThrowSQLException_ForNegativeRegionId(int regionId) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 0;
        int size = 10;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(SQLException.class, () -> provinceRepository.findAll(regionId, page, size));

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

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void findAllTotal_ShouldThrowSQLException_ForNegativeRegionId(int regionId) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(SQLException.class, () -> provinceRepository.findAllTotal(regionId));

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

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @MethodSource("negativeRegionId_And_nullAndBlankNameValues")
    void searchByName_ShouldThrowSQLException_ForNegativeRegionId_And_NullAndBlankName(int regionId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(SQLException.class, () -> provinceRepository.searchByName(regionId, name));

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
    void searchByNamePaged_ShouldThrowBadSqlGrammarException_ForInvalidPageNumberAndPageSize(int page, int size) {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        String name = "name".toLowerCase();

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(BadSqlGrammarException.class, () -> provinceRepository.searchByName(regionId, name, page, size));

        // Behavior Verifications

        // Assertions
    }

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @MethodSource("negativeRegionId_And_nullAndBlankNameValues")
    void searchByNamePaged_ShouldThrowSQLException_ForNegativeRegionId_And_NullAndBlankName(int regionId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 1;

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(SQLException.class, () -> provinceRepository.searchByName(regionId, name, page, size));

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

    @Disabled("Will be enabled when stored procedures with input validation is created")
    @ParameterizedTest
    @MethodSource("negativeRegionId_And_nullAndBlankNameValues")
    void searchByNameTotal_ShouldThrowSQLException_ForNegativeRegionId_And_NullAndBlankName(int regionId, String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        assertThrowsExactly(SQLException.class, () -> provinceRepository.searchByNameTotal(regionId, name));

        // Behavior Verifications

        // Assertions
    }

    private static Stream<Arguments> negativeRegionId_And_nullAndBlankNameValues() {
        return Stream.of(
                // Null and blank values
                Arguments.of(1, null),
                Arguments.of(1, ""),
                Arguments.of(1, "  "),

                // Negative numbers
                Arguments.of(-1, "name"),
                Arguments.of(-10, "name")
        );
    }
}