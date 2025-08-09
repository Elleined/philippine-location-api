package com.elleined.philippine_location_api.city;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CityService cityService;

    @Test
    void getAllBy_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        List<CityDTO> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(cityService.getAll(anyInt(), anyInt())).thenReturn(expected);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/{provinceId}/cities", regionId, provinceId))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", isA(List.class)))
                    .andExpect(jsonPath("$", empty()));
        });

        // Behavior Verifications
        verify(cityService).getAll(anyInt(), anyInt());

        // Assertions
    }

    @Test
    void getAllByPaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Page<City> expected = new Page<>(new ArrayList<>(), request, 10);

        // Set up method

        // Stubbing methods
        when(cityService.getAll(anyInt(), anyInt(), any(PageRequest.class))).thenReturn(expected);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/{provinceId}/cities/paged", regionId, provinceId)
                            .param("page", String.valueOf(page))
                            .param("size", String.valueOf(size)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content", isA(List.class)))
                    .andExpect(jsonPath("$.content", empty()))
                    .andExpect(jsonPath("$.content.length()", lessThanOrEqualTo(size)))
                    .andExpect(jsonPath("$.request.page", is(page)))
                    .andExpect(jsonPath("$.request.size", is(size)))
                    .andExpect(jsonPath("$.totalElements", isA(Number.class)));
        });

        // Behavior Verifications
        verify(cityService).getAll(anyInt(), anyInt(), any(PageRequest.class));

        // Assertions
    }

    @Test
    void getAllByPaged_ShouldHaveTheDefault_PageOf1_AndSizeOf10_WhenNotProvided() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Page<City> expected = new Page<>(new ArrayList<>(), request, 10);

        // Set up method

        // Stubbing methods
        when(cityService.getAll(anyInt(), anyInt(), any(PageRequest.class))).thenReturn(expected);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/{provinceId}/cities/paged", regionId, provinceId))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content", isA(List.class)))
                    .andExpect(jsonPath("$.content", empty()))
                    .andExpect(jsonPath("$.content.length()", lessThanOrEqualTo(size)))
                    .andExpect(jsonPath("$.request.page", is(page)))
                    .andExpect(jsonPath("$.request.size", is(size)))
                    .andExpect(jsonPath("$.totalElements", isA(Number.class)));
        });

        // Behavior Verifications
        verify(cityService).getAll(anyInt(), anyInt(), any(PageRequest.class));

        // Assertions
    }

    @Test
    void searchByName_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        String name = "name";
        List<City> expected = new ArrayList<>();

        // Set up method
        when(cityService.searchByName(anyInt(), anyInt(), anyString())).thenReturn(expected);

        // Stubbing methods
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/{provinceId}/cities/search", regionId, provinceId)
                            .param("name", name))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", isA(List.class)))
                    .andExpect(jsonPath("$", empty()));
        });

        // Calling the method
        verify(cityService).searchByName(anyInt(), anyInt(), anyString());

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
        String name = "name";
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Page<City> expected = new Page<>(new ArrayList<>(), request, 10);

        // Set up method
        when(cityService.searchByName(anyInt(), anyInt(), anyString(), any(PageRequest.class))).thenReturn(expected);

        // Stubbing methods

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/{provinceId}/cities/search/paged", regionId, provinceId)
                            .param("name", name)
                            .param("page", String.valueOf(page))
                            .param("size", String.valueOf(size)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content", isA(List.class)))
                    .andExpect(jsonPath("$.content", empty()))
                    .andExpect(jsonPath("$.content.length()", lessThanOrEqualTo(size)))
                    .andExpect(jsonPath("$.request.page", is(page)))
                    .andExpect(jsonPath("$.request.size", is(size)))
                    .andExpect(jsonPath("$.totalElements", isA(Number.class)));
        });

        // Behavior Verifications
        verify(cityService).searchByName(anyInt(), anyInt(), anyString(), any(PageRequest.class));

        // Assertions
    }

    @Test
    void searchByNamePaged_ShouldHaveTheDefault_PageOf1_AndSizeOf10_WhenNotProvided() {
        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        String name = "name";
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Page<City> expected = new Page<>(new ArrayList<>(), request, 10);

        // Set up method
        when(cityService.searchByName(anyInt(), anyInt(), anyString(), any(PageRequest.class))).thenReturn(expected);

        // Stubbing methods

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/{provinceId}/cities/search/paged", regionId, provinceId)
                            .param("name", name))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content", isA(List.class)))
                    .andExpect(jsonPath("$.content", empty()))
                    .andExpect(jsonPath("$.content.length()", lessThanOrEqualTo(size)))
                    .andExpect(jsonPath("$.request.page", is(page)))
                    .andExpect(jsonPath("$.request.size", is(size)))
                    .andExpect(jsonPath("$.totalElements", isA(Number.class)));
        });

        // Behavior Verifications
        verify(cityService).searchByName(anyInt(), anyInt(), anyString(), any(PageRequest.class));

        // Assertions
    }
}