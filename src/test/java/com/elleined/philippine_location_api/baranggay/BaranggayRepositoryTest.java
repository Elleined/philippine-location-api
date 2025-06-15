package com.elleined.philippine_location_api.baranggay;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.elleined.philippine_location_api.city.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
}