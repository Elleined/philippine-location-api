package com.elleined.philippine_location_api.city;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.elleined.philippine_location_api.region.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@DataJdbcTest
@TestPropertySource(locations = "classpath:.env.test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

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
        assertEquals(expected, cities);
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
        assertEquals(expected, cities);
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
        assertTrue(total >= 0);
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
        assertEquals(expected, cities);
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
        assertTrue(contains);
        assertEquals(expected, cities);
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
        assertTrue(total >= 0);
    }
}