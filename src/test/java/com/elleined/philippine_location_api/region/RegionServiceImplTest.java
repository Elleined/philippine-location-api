package com.elleined.philippine_location_api.region;

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
class RegionServiceImplTest {

    private static ExecutableValidator executableValidator;

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionServiceImpl regionService;

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
        List<Region> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(regionRepository.findAll()).thenReturn(expected);

        // Calling the method
        List<Region> actual = assertDoesNotThrow(() -> regionService.getAll());

        // Behavior Verifications
        verify(regionRepository).findAll();

        // Assertions
        assertEquals(expected, actual);
    }

    @Test
    void getAllPaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 10;
        int totalElements = 10;
        PageRequest request = PageRequest.of(page, size);
        List<Region> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(regionRepository.findAll(anyInt(), anyInt())).thenReturn(expected);
        when(regionRepository.findAllTotal()).thenReturn(totalElements);

        // Calling the method
        Page<Region> actual = assertDoesNotThrow(() -> regionService.getAll(request));

        // Behavior Verifications
        verify(regionRepository).findAll(anyInt(), anyInt());
        verify(regionRepository).findAllTotal();

        // Assertions
        assertNotNull(actual);
        assertEquals(expected, actual.content());
    }

    private static Stream<Arguments> nullPageRequestValues() {
        return Stream.of(
                Arguments.of((PageRequest) null)
        );
    }

    @ParameterizedTest
    @MethodSource("nullPageRequestValues")
    void getAllPaged_ShouldThrowConstraintViolationException_ForNullPageRequestInput(PageRequest request) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<RegionService>> violations = executableValidator.validateParameters(
                regionService,
                RegionService.class.getMethod("getAll", PageRequest.class),
                new Object[]{request}
        );

        // Behavior Verifications
        verifyNoInteractions(regionRepository);

        // Assertions

        assertEquals(1, violations.size());
    }


    @Test
    void searchByName_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        List<Region> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(regionRepository.searchByName(anyString())).thenReturn(expected);

        // Calling the method
        List<Region> actual = assertDoesNotThrow(() -> regionService.searchByName("name"));

        // Behavior Verifications
        verify(regionRepository).searchByName(anyString());

        // Assertions
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> nullAndBlankNameValues() {
        return Stream.of(
                Arguments.of((String) null),
                Arguments.of(""),
                Arguments.of("  ")
        );
    }

    @ParameterizedTest
    @MethodSource("nullAndBlankNameValues")
    void searchByName_ShouldThrowConstraintViolationException_ForNullInputs(String name) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<RegionService>> violations = executableValidator.validateParameters(
                regionService,
                RegionService.class.getMethod("searchByName", String.class),
                new Object[]{name}
        );

        // Behavior Verifications
        verifyNoInteractions(regionRepository);

        // Assertions
        assertEquals(1, violations.size());
    }

    @Test
    void searchByNamePaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 10;
        int totalElements = 10;
        PageRequest request = PageRequest.of(page, size);
        String name = "name";
        List<Region> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(regionRepository.searchByName(anyString(), anyInt(), anyInt())).thenReturn(expected);
        when(regionRepository.searchByNameTotal(anyString())).thenReturn(totalElements);

        // Calling the method
        Page<Region> actual = assertDoesNotThrow(() -> regionService.searchByName(request, name));

        // Behavior Verifications
        verify(regionRepository).searchByName(anyString(), anyInt(), anyInt());
        verify(regionRepository).searchByNameTotal(anyString());

        // Assertions
        assertEquals(expected, actual.content());
    }

    private static Stream<Arguments> nullPageRequest_AndBlankAndNullNameValues() {
        PageRequest request = PageRequest.of(1, 10);
        String name = "name";

        return Stream.of(
                Arguments.of(request, "  "),
                Arguments.of(request, ""),
                Arguments.of(request, null),

                Arguments.of(null, name)
        );
    }

    @ParameterizedTest
    @MethodSource("nullPageRequest_AndBlankAndNullNameValues")
    void searchByNamePaged_ShouldThrowConstraintViolationException_ForNullPageRequest_AndBlankAndNullInputs(PageRequest request, String name) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<RegionService>> violations = executableValidator.validateParameters(
                regionService,
                RegionService.class.getMethod("searchByName", PageRequest.class, String.class),
                new Object[]{request, name}
        );

        // Behavior Verifications
        verifyNoInteractions(regionRepository);

        // Assertions
        assertEquals(1, violations.size());
    }
}