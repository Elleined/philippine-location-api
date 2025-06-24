package com.elleined.philippine_location_api.city;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.province.ProvinceService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ExecutableValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    private static ExecutableValidator executableValidator;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeAll
    static void setupAll() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        executableValidator = validator.forExecutables();
    }

    @Test
    void getAll_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        List<City> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(cityRepository.findAll(anyInt(), anyInt())).thenReturn(expected);

        // Calling the method
        List<City> actual = assertDoesNotThrow(() -> cityService.getAll(regionId, provinceId));

        // Behavior Verifications
        verify(cityRepository).findAll(anyInt(), anyInt());

        // Assertions
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceIdValues() {
        int regionId = 1;
        int provinceId = 1;

        return Stream.of(
                Arguments.of(-1, provinceId),
                Arguments.of(0, provinceId),

                Arguments.of(regionId, -1),
                Arguments.of(regionId, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceIdValues")
    void getAll_ShouldThrowConstraintViolationException_ForNegativeRegionId_AndNegativeProvinceIdInput(int regionId, int provinceId) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<CityService>> violations = executableValidator.validateParameters(
                cityService,
                CityService.class.getMethod("getAll", int.class, int.class),
                new Object[]{regionId, provinceId}
        );

        // Behavior Verifications
        verifyNoInteractions(cityRepository);

        // Assertions
        assertFalse(violations.isEmpty());
    }

    @Test
    void getAllPaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        int page = 1;
        int size = 10;
        PageRequest request = PageRequest.of(page, size);
        List<City> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(cityRepository.findAll(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(expected);
        when(cityRepository.findAllTotal(anyInt(), anyInt())).thenReturn(expected.size());

        // Calling the method
        Page<City> actual = assertDoesNotThrow(() -> cityService.getAll(regionId, provinceId, request));

        // Behavior Verifications
        verify(cityRepository).findAll(anyInt(), anyInt(), anyInt(), anyInt());
        verify(cityRepository).findAllTotal(anyInt(), anyInt());

        // Assertions
        assertEquals(expected, actual.content());
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceId_AndNullPageRequest() {
        int regionId = 1;
        int provinceId = 1;
        PageRequest request = PageRequest.of(1, 10);

        return Stream.of(
                Arguments.of(-1, provinceId, request),
                Arguments.of(0, provinceId, request),

                Arguments.of(regionId, -1, request),
                Arguments.of(regionId, 0, request),

                Arguments.of(regionId, provinceId, null)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNullPageRequest")
    void getAllPaged_ShouldThrowConstraintViolationException_ForNegativeRegionId_AndNegativeProvinceId_AndNullPageRequest(int regionId, int provinceId, PageRequest request) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<CityService>> violations = executableValidator.validateParameters(
                cityService,
                CityService.class.getMethod("getAll", int.class, int.class, PageRequest.class),
                new Object[]{regionId, provinceId, request}
        );

        // Behavior Verifications
        verifyNoInteractions(cityRepository);

        // Assertions
        assertFalse(violations.isEmpty());
    }

    @Test
    void searchByName_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        List<City> expected = new ArrayList<>();
        String name = "name";

        // Set up method

        // Stubbing methods
        when(cityRepository.searchByName(anyInt(), anyInt(), anyString())).thenReturn(expected);

        // Calling the method
        List<City> actual = assertDoesNotThrow(() -> cityService.searchByName(regionId, provinceId, name));

        // Behavior Verifications
        verify(cityRepository).searchByName(anyInt(), anyInt(), anyString());

        // Assertions
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceId_AndNullAndBlankNameValues() {
        int regionId = 1;
        int provinceId = 1;
        String name = "name";

        return Stream.of(
                Arguments.of(-1, provinceId, name),
                Arguments.of(0, provinceId, name),

                Arguments.of(regionId, -1, name),
                Arguments.of(regionId, 0, name),

                Arguments.of(regionId, provinceId, ""),
                Arguments.of(regionId, provinceId, "   "),
                Arguments.of(regionId, provinceId, null)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNullAndBlankNameValues")
    void searchByName_ShouldThrowConstraintViolationException_ForNegativeRegionId_AndNegativeProvinceId_AndNullAndBlankNameInput(int regionId, int provinceId, String name) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<CityService>> violations = executableValidator.validateParameters(
                cityService,
                CityService.class.getMethod("searchByName", int.class, int.class, String.class),
                new Object[]{regionId, provinceId, name}
        );

        // Behavior Verifications
        verifyNoInteractions(cityRepository);

        // Assertions
    }

    @Test
    void searchByNamePaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        String name = "name";
        int page = 1;
        int size = 10;
        PageRequest request = PageRequest.of(page, size);
        List<City> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(cityRepository.searchByName(anyInt(), anyInt(), anyString(), anyInt(), anyInt())).thenReturn(expected);
        when(cityRepository.searchByNameTotal(anyInt(), anyInt(), anyString())).thenReturn(expected.size());

        // Calling the method
        Page<City> actual = assertDoesNotThrow(() -> cityService.searchByName(regionId, provinceId, name, request));

        // Behavior Verifications
        verify(cityRepository).searchByName(anyInt(), anyInt(), anyString(), anyInt(), anyInt());
        verify(cityRepository).searchByNameTotal(anyInt(), anyInt(), anyString());

        // Assertions
        assertEquals(expected, actual.content());
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceId_AndNullAndBlankName_AndNullPageRequestValues() {
        int regionId = 1;
        int provinceId = 1;
        String name = "name";
        PageRequest request = PageRequest.of(1, 10);

        return Stream.of(
                Arguments.of(-1, provinceId, name, request),
                Arguments.of(0, provinceId, name, request),

                Arguments.of(regionId, -1, name, request),
                Arguments.of(regionId, 0, name, request),

                Arguments.of(regionId, provinceId, "", request),
                Arguments.of(regionId, provinceId, "   ", request),
                Arguments.of(regionId, provinceId, null, request),

                Arguments.of(regionId, provinceId, name, null)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNullAndBlankName_AndNullPageRequestValues")
    void searchByNamePaged_ShouldThrowConstraintViolationException_ForNegativeRegionId_AndNegativeProvinceId_AndNullAndBlankName_AndNullPageRequestInput(int regionId, int provinceId, String name, PageRequest request) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<CityService>> violations = executableValidator.validateParameters(
                cityService,
                CityService.class.getMethod("searchByName", int.class, int.class, String.class, PageRequest.class),
                new Object[]{regionId, provinceId, name, request}
        );


        // Behavior Verifications
        verifyNoInteractions(cityRepository);

        // Assertions
        assertFalse(violations.isEmpty());
    }
}