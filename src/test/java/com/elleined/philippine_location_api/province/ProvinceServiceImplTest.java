package com.elleined.philippine_location_api.province;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
import com.elleined.philippine_location_api.region.Region;
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
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

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
        AggregateReference<Region, Long> region = AggregateReference.to(1L);
        List<Province> provinces = List.of(new Province(1L, null, region));
        List<ProvinceDTO> expected = provinces.stream()
                .map(Province::toDTO)
                .toList();

        // Set up method

        // Stubbing methods
        when(provinceRepository.findAll(anyInt())).thenReturn(provinces);

        // Calling the method
        List<ProvinceDTO> actual = assertDoesNotThrow(() -> provinceService.getAll(regionId));

        // Behavior Verifications
        verify(provinceRepository).findAll(anyInt());

        // Assertions
        assertThat(actual).isEqualTo(expected);
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
        assertThat(violations).isNotEmpty();
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
        when(provinceRepository.findAllTotal(anyInt())).thenReturn(expected.size());

        // Calling the method
        Pageable<Province> actual = assertDoesNotThrow(() -> provinceService.getAll(regionId, request));

        // Behavior Verifications
        verify(provinceRepository).findAll(anyInt(), anyInt(), anyInt());
        verify(provinceRepository).findAllTotal(anyInt());

        // Assertions
        assertThat(actual.content()).isEqualTo(expected);
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
        assertThat(violations).isNotEmpty();
    }

    @Test
    void searchByName_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        List<Province> expected = new ArrayList<>();
        String name = "name";

        // Set up method

        // Stubbing method
        when(provinceRepository.findAllBy(anyInt(), anyString())).thenReturn(expected);

        // Calling the method
        List<Province> actual = assertDoesNotThrow(() -> provinceService.findAllByTotal(regionId, name));

        // Behavior Verifications
        verify(provinceRepository).findAllBy(anyInt(), anyString());

        // Assertions
        assertThat(actual).isEqualTo(expected);
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
        assertThat(violations).isNotEmpty();
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
        when(provinceRepository.findAllBy(anyInt(), anyString(), anyInt(), anyInt())).thenReturn(expected);
        when(provinceRepository.findAllByTotal(anyInt(), anyString())).thenReturn(expected.size());

        // Calling the method
        Pageable<Province> actual = assertDoesNotThrow(() -> provinceService.findAllBy(regionId, name, request));

        // Behavior Verifications
        verify(provinceRepository).findAllBy(anyInt(), anyString(), anyInt(), anyInt());
        verify(provinceRepository).findAllByTotal(anyInt(), anyString());

        // Assertions
        assertThat(actual.content()).isEqualTo(expected);
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
        assertThat(violations).isNotEmpty();
    }
}