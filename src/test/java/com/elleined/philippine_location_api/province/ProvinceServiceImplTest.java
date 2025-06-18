package com.elleined.philippine_location_api.province;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.region.RegionService;
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
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class ProvinceServiceImplTest {

    private static ExecutableValidator executableValidator;

    @Mock
    private ProvinceRepository provinceRepository;

    @InjectMocks
    private ProvinceServiceImpl provinceService;

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
        List<Province> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(provinceRepository.findAll(anyInt())).thenReturn(expected);

        // Calling the method
        List<Province> actual = assertDoesNotThrow(() -> provinceService.getAll(regionId));

        // Behavior Verifications
        verify(provinceRepository).findAll(anyInt());

        // Assertions
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    void getAll_ShouldThrowConstraintViolationException_ForNegativeRegionIdInput(int regionId) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<ProvinceService>> violations = executableValidator.validateParameters(
                provinceService,
                ProvinceService.class.getMethod("getAll", int.class),
                new Object[]{regionId}
        );

        // Behavior Verifications
        verifyNoInteractions(provinceRepository);

        // Assertions
        assertFalse(violations.isEmpty());
    }

    @Test
    void getAllPaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int page = 1;
        int size = 10;
        PageRequest request = PageRequest.of(page, size);
        List<Province> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(provinceRepository.findAll(anyInt(), anyInt(), anyInt())).thenReturn(expected);

        // Calling the method
        Page<Province> actual = assertDoesNotThrow(() -> provinceService.getAll(regionId, request));

        // Behavior Verifications
        verify(provinceRepository).findAll(anyInt(), anyInt(), anyInt());

        // Assertions
        assertEquals(expected, actual.content());
    }

    private static Stream<Arguments> negativeRegionIdValues_AndNullPageRequest() {
        PageRequest request = PageRequest.of(1, 10);
        int regionId = 1;

        return Stream.of(
                Arguments.of(-1, request),
                Arguments.of(0, request),

                Arguments.of(regionId, null)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRegionIdValues_AndNullPageRequest")
    void getAllPaged_ShouldConstraintViolationException_ForNegativeRegionId_AndNullPageRequest(int regionId, PageRequest request) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<ProvinceService>> violations = executableValidator.validateParameters(
                provinceService,
                ProvinceService.class.getMethod("getAll", int.class, PageRequest.class),
                new Object[]{regionId, request}
        );

        // Behavior Verifications
        verifyNoInteractions(provinceRepository);

        // Assertions
        assertFalse(violations.isEmpty());
    }

    @Test
    void searchByName_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        List<Province> expected = new ArrayList<>();

        // Set up method

        // Stubbing method
        when(provinceRepository.searchByName(anyInt(), anyString())).thenReturn(expected);

        // Calling the method
        List<Province> actual = assertDoesNotThrow(() -> provinceService.searchByName(regionId, "name"));

        // Behavior Verifications
        verify(provinceRepository).searchByName(anyInt(), anyString());

        // Assertions
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> negativeRegionId_AndNullAndBlankNameValues() {
        int  regionId = 1;
        String name = "name";

        return Stream.of(
                Arguments.of(regionId, "  "),
                Arguments.of(regionId, ""),
                Arguments.of(regionId, null),

                Arguments.of(-1, name),
                Arguments.of(0, name)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNullAndBlankNameValues")
    void searchByName_ShouldThrowConstraintViolationException_ForNegativeRegionId_AndNullAndBlankName(int regionId, String name) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing method
        Set<ConstraintViolation<ProvinceService>> violations = executableValidator.validateParameters(
                provinceService,
                ProvinceService.class.getMethod("searchByName", int.class, String.class),
                new Object[]{regionId, name}
        );

        // Calling the method

        // Behavior Verifications
        verifyNoInteractions(provinceRepository);

        // Assertions
        assertFalse(violations.isEmpty());
    }

    @Test
    void searchByNamePaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        String name = "name";
        int page = 1;
        int size = 10;
        PageRequest request = PageRequest.of(page, size);
        List<Province> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(provinceRepository.searchByName(anyInt(), anyString(), anyInt(), anyInt())).thenReturn(expected);

        // Calling the method
        Page<Province> actual = assertDoesNotThrow(() -> provinceService.searchByName(regionId, name, request));

        // Behavior Verifications
        verify(provinceRepository).searchByName(anyInt(), anyString(), anyInt(), anyInt());

        // Assertions
        assertEquals(expected, actual.content());
    }

    private static Stream<Arguments> negativeRegionId_AndNullAndBlankName_AndNullPageRequestValues() {
        int  regionId = 1;
        String name = "name";
        PageRequest request = PageRequest.of(1, 10);

        return Stream.of(
                Arguments.of(regionId, "  ", request),
                Arguments.of(regionId, "", request),
                Arguments.of(regionId, null, request),

                Arguments.of(-1, name, request),
                Arguments.of(0, name, request),

                Arguments.of(regionId, name, null)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNullAndBlankName_AndNullPageRequestValues")
    void searchByNamePaged_ShouldThrowConstraintException_ForNegativeRegionId_AndNullAndBlankName_AndNullPageRequest(int regionId, String name, PageRequest request) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        Set<ConstraintViolation<ProvinceService>> violations = executableValidator.validateParameters(
                provinceService,
                ProvinceService.class.getMethod("searchByName", int.class, String.class, PageRequest.class),
                new Object[]{regionId, name, request}
        );

        // Calling the method

        // Behavior Verifications
        verifyNoInteractions(provinceRepository);

        // Assertions
        assertFalse(violations.isEmpty());
    }
}