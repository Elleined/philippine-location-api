package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegionController.class)
class RegionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RegionService regionService;

    @Test
    void getAll_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        when(regionService.getAll()).thenReturn(new ArrayList<>());

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions"))
                    .andExpect(status().isOk());
        });

        // Behavior Verifications
        verify(regionService).getAll();

        // Assertions
    }

    @Test
    void getAllPaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method
        Page<Region> regions = new Page<>(new ArrayList<>(), new PageRequest(1, 10), 10);

        // Stubbing methods
        when(regionService.getAll(any(PageRequest.class))).thenReturn(regions);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/paged")
                            .param("page", "1")
                            .param("size", "10"))
                    .andExpect(status().isOk());
        });

        // Behavior Verifications
        verify(regionService).getAll(any(PageRequest.class));

        // Assertions
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 10",
            "10, -1"
    })
    void getAllPaged_ShouldThrowBadRequest_ForNegativePage_AndNegativeSize(int page, int size) throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        mockMvc.perform(get("/regions/paged")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(status().isBadRequest());

        // Behavior Verifications
        verifyNoInteractions(regionService);

        // Assertions
    }

    @Test
    void getAllPaged_ShouldHaveTheDefault_PageOf1_AndSizeOf10_WhenNotProvided() throws Exception {
        // Pre defined values

        // Expected Value
        int page = 1;
        int size = 10;

        // Mock data
        Page<Region> regions = new Page<>(new ArrayList<>(), new PageRequest(page, size), 10);

        // Set up method

        // Stubbing methods
        when(regionService.getAll(any(PageRequest.class))).thenReturn(regions);

        // Calling the method
        mockMvc.perform(get("/regions/paged")
                        .param("name", "name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.request.page").value(page))
                .andExpect(jsonPath("$.request.size").value(size));

        // Behavior Verifications
        verify(regionService).getAll(any(PageRequest.class));

        // Assertions
    }

    @Test
    void searchByName_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        when(regionService.searchByName(anyString())).thenReturn(new ArrayList<>());

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/search")
                            .param("name", "name"))
                    .andExpect(status().isOk());
        });

        // Behavior Verifications
        verify(regionService).searchByName(anyString());

        // Assertions
    }

    private static Stream<Arguments> searchByName_nullAndBlankNameValues() {
        return Stream.of(
                Arguments.of((String) null),
                Arguments.of(""),
                Arguments.of("  ")
        );
    }

    @ParameterizedTest
    @MethodSource("searchByName_nullAndBlankNameValues")
    void searchByName_ShouldReturnBadRequest_ForNullAndBlankName(String name) {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        when(regionService.searchByName(anyString())).thenReturn(new ArrayList<>());

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/search")
                            .param("name", name))
                    .andExpect(status().isBadRequest());
        });

        // Behavior Verifications
        verifyNoInteractions(regionService);

        // Assertions
    }

    @Test
    void searchByNamePaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        Page<Region> regions = new Page<>(new ArrayList<>(), new PageRequest(1, 10), 10);

        // Set up method

        // Stubbing methods
        when(regionService.searchByName(any(PageRequest.class), anyString())).thenReturn(regions);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/paged-search")
                            .param("name", "name")
                            .param("page", "1")
                            .param("size", "10"))
                    .andExpect(status().isOk());
        });

        // Behavior Verifications
        verify(regionService).searchByName(any(PageRequest.class), anyString());

        // Assertions
    }

    private static Stream<Arguments> searchByNamePaged_NegativePage_AndNegativeSize_AndNullAndBlankNameValues() {
        String name = "name";
        int page = 1;
        int size = 10;

        return Stream.of(
                Arguments.of(null, page, size),
                Arguments.of("", page, size),
                Arguments.of("  ", page, size),

                Arguments.of(name, -1, 10),
                Arguments.of(name, 10, -1)
        );
    }

    @ParameterizedTest
    @MethodSource("searchByNamePaged_NegativePage_AndNegativeSize_AndNullAndBlankNameValues")
    void searchByNamePaged_ShouldReturnBadRequest_ForNegativePage_AndNegativeSize_AndNullAndBlankNameValues(String name, int page, int size) throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        mockMvc.perform(get("/regions/paged-search")
                        .param("name", name)
                        .param("page", "" + page)
                        .param("size", "" + size))
                .andExpect(status().isBadRequest());

        // Calling the method

        // Behavior Verifications
        verifyNoInteractions(regionService);

        // Assertions
    }

    @Test
    void searchByNamePaged_ShouldHaveTheDefault_PageOf1_AndSizeOf10_WhenNotProvided() throws Exception {
        // Pre defined values

        // Expected Value
        int page = 1;
        int size = 10;

        // Mock data
        Page<Region> regions = new Page<>(new ArrayList<>(), new PageRequest(page, size), 10);

        // Set up method

        // Stubbing methods
        when(regionService.searchByName(any(PageRequest.class), anyString())).thenReturn(regions);

        // Calling the method
        mockMvc.perform(get("/regions/paged-search")
                        .param("name", "name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.request.page").value(page))
                .andExpect(jsonPath("$.request.size").value(size));

        // Behavior Verifications
        verify(regionService).searchByName(any(PageRequest.class), anyString());

        // Assertions
    }
}