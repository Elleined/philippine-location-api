package com.elleined.philippine_location_api.baranggay;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BaranggayServiceImplTest {

    private static ExecutableValidator executableValidator;

    @Mock
    private BaranggayRepository baranggayRepository;

    @InjectMocks
    private BaranggayServiceImpl baranggayService;

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
        int cityId = 1;
        List<Baranggay> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(baranggayRepository.findAll(anyInt(), anyInt(), anyInt())).thenReturn(expected);

        // Calling the method
        List<Baranggay> actual = assertDoesNotThrow(() -> baranggayService.getAll(regionId, provinceId, cityId));

        // Behavior Verifications
        verify(baranggayRepository).findAll(anyInt(), anyInt(), anyInt());

        // Assertions
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceId_AndNegativeCityIdValues() {
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;

        return Stream.of(
                Arguments.of(-1, provinceId, cityId),
                Arguments.of(0, provinceId, cityId),

                Arguments.of(regionId, -1, cityId),
                Arguments.of(regionId, 0, cityId),

                Arguments.of(regionId, provinceId, -1),
                Arguments.of(regionId, provinceId, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityIdValues")
    void getAll_ShouldThrowConstraintViolationException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityIdInput(int regionId, int provinceId, int cityId) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<BaranggayService>> violations = executableValidator.validateParameters(
                baranggayService,
                BaranggayService.class.getMethod("getAll", int.class, int.class, int.class),
                new Object[]{regionId, provinceId, cityId}
        );

        // Behavior Verifications
        verifyNoInteractions(baranggayRepository);

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
        int cityId = 1;
        List<Baranggay> expected = new ArrayList<>();
        int page = 1;
        int size = 10;
        PageRequest request = PageRequest.of(page, size);

        // Set up method

        // Stubbing methods
        when(baranggayRepository.findAll(anyInt(), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(expected);
        when(baranggayRepository.findAllTotal(anyInt(), anyInt(), anyInt())).thenReturn(expected.size());

        // Calling the method
        Page<Baranggay> actual = assertDoesNotThrow(() -> baranggayService.getAll(regionId, provinceId, cityId, request));

        // Behavior Verifications
        verify(baranggayRepository).findAll(anyInt(), anyInt(), anyInt(), anyInt(), anyInt());
        verify(baranggayRepository).findAllTotal(anyInt(), anyInt(), anyInt());

        // Assertions
        assertEquals(expected, actual.content());
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullPageRequest() {
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;
        PageRequest request = PageRequest.of(1, 10);

        return Stream.of(
                Arguments.of(-1, provinceId, cityId, request),
                Arguments.of(0, provinceId, cityId, request),

                Arguments.of(regionId, -1, cityId, request),
                Arguments.of(regionId, 0, cityId, request),

                Arguments.of(regionId, provinceId, -1, request),
                Arguments.of(regionId, provinceId, 0, request),

                Arguments.of(regionId, provinceId, cityId, null)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullPageRequest")
    void getAllPaged_ShouldThrowConstraintViolationException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullPageRequestInput(int regionId, int provinceId, int cityId, PageRequest request) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<BaranggayService>> violations = executableValidator.validateParameters(
                baranggayService,
                BaranggayService.class.getMethod("getAll", int.class, int.class, int.class, PageRequest.class),
                new Object[]{regionId, provinceId, cityId, request}
        );

        // Behavior Verifications
        verifyNoInteractions(baranggayRepository);

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
        int cityId = 1;
        String name = "name";
        List<Baranggay> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(baranggayRepository.searchByName(anyInt(), anyInt(), anyInt(), anyString())).thenReturn(expected);

        // Calling the method
        List<Baranggay> actual = assertDoesNotThrow(() -> baranggayService.searchByName(regionId, provinceId, cityId, name));

        // Behavior Verifications
        verify(baranggayRepository).searchByName(anyInt(), anyInt(), anyInt(), anyString());

        // Assertions
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankNameValues() {
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;
        String name = "name";

        return Stream.of(
                Arguments.of(-1, provinceId, cityId, name),
                Arguments.of(0, provinceId, cityId, name),

                Arguments.of(regionId, -1, cityId, name),
                Arguments.of(regionId, 0, cityId, name),

                Arguments.of(regionId, provinceId, -1, name),
                Arguments.of(regionId, provinceId, 0, name),

                Arguments.of(regionId, provinceId, cityId, ""),
                Arguments.of(regionId, provinceId, cityId, "      "),
                Arguments.of(regionId, provinceId, cityId, null)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankNameValues")
    void searchByName_ShouldThrowConstraintViolationException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankNameInput(int regionId, int provinceId, int cityId, String name) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<BaranggayService>> violations = executableValidator.validateParameters(
                baranggayService,
                BaranggayService.class.getMethod("searchByName", int.class, int.class, int.class, String.class),
                new Object[]{regionId, provinceId, cityId, name}
        );

        // Behavior Verifications
        verifyNoInteractions(baranggayRepository);

        // Assertions
        assertFalse(violations.isEmpty());
    }

    @Test
    void searchByNamePaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;
        String name = "name";
        List<Baranggay> expected = new ArrayList<>();
        int page = 1;
        int size = 10;
        PageRequest request = PageRequest.of(page, size);

        // Set up method

        // Stubbing methods
        when(baranggayRepository.searchByName(anyInt(), anyInt(), anyInt(), anyString(), anyInt(), anyInt())).thenReturn(expected);
        when(baranggayRepository.searchByNameTotal(anyInt(), anyInt(), anyInt(), anyString())).thenReturn(expected.size());

        // Calling the method
        assertDoesNotThrow(() -> baranggayService.searchByName(regionId, provinceId, cityId, name, request));

        // Behavior Verifications
        verify(baranggayRepository).searchByName(anyInt(), anyInt(), anyInt(), anyString(), anyInt(), anyInt());
        verify(baranggayRepository).searchByNameTotal(anyInt(), anyInt(), anyInt(), anyString());

        // Assertions
    }

    private static Stream<Arguments> negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankName_AndNullPageRequestValues() {
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;
        String name = "name";
        PageRequest request = PageRequest.of(1, 10);

        return Stream.of(
                Arguments.of(-1, provinceId, cityId, name, request),
                Arguments.of(0, provinceId, cityId, name, request),

                Arguments.of(regionId, -1, cityId, name, request),
                Arguments.of(regionId, 0, cityId, name, request),

                Arguments.of(regionId, provinceId, -1, name, request),
                Arguments.of(regionId, provinceId, 0, name, request),

                Arguments.of(regionId, provinceId, cityId, "", request),
                Arguments.of(regionId, provinceId, cityId, "      ", request),
                Arguments.of(regionId, provinceId, cityId, null, request),

                Arguments.of(regionId, provinceId, cityId, name, null)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankName_AndNullPageRequestValues")
    void searchByNamePaged_ShouldThrowConstraintViolationException_ForNegativeRegionId_AndNegativeProvinceId_AndNegativeCityId_AndNullAndBlankName_AndNullPageRequestInput(int regionId, int provinceId, int cityId, String name, PageRequest request) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<BaranggayService>> violations = executableValidator.validateParameters(
                baranggayService,
                BaranggayService.class.getMethod("searchByName", int.class, int.class, int.class, String.class, PageRequest.class),
                new Object[]{regionId, provinceId, cityId, name, request}
        );

        // Behavior Verifications
        verifyNoInteractions(baranggayRepository);

        // Assertions
        assertFalse(violations.isEmpty());
    }
}