package com.elleined.philippine_location_api.region;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        int page = 1;
        int size = 10;

        // Set up method
        Page<Region> regions = new Page<>(new ArrayList<>(), new PageRequest(page, size), 10);

        // Stubbing methods
        when(regionService.getAll(any(PageRequest.class))).thenReturn(regions);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/paged")
                            .param("page", String.valueOf(page))
                            .param("size", String.valueOf(size)))
                    .andExpect(status().isOk());
        });

        // Behavior Verifications
        verify(regionService).getAll(any(PageRequest.class));

        // Assertions
    }

    @Test
    void getAllPaged_ShouldHaveTheDefault_PageOf1_AndSizeOf10_WhenNotProvided() {
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
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/paged")
                            .param("page", String.valueOf(page))
                            .param("size", String.valueOf(size)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.request.page").value(page))
                    .andExpect(jsonPath("$.request.size").value(size));
        });

        // Behavior Verifications
        verify(regionService).getAll(any(PageRequest.class));

        // Assertions
    }

    @Test
    void searchByName_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        String name = "3";

        // Set up method

        // Stubbing methods
        when(regionService.searchByName(anyString())).thenReturn(new ArrayList<>());

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/search")
                            .param("name", name))
                    .andExpect(status().isOk());
        });

        // Behavior Verifications
        verify(regionService).searchByName(anyString());

        // Assertions
    }

    @Test
    void searchByNamePaged_HappyPath() {
        // Pre defined values

        // Expected Value
        String name = "3";
        int page = 1;
        int size = 10;

        // Mock data
        Page<Region> regions = new Page<>(new ArrayList<>(), new PageRequest(page, size), 10);

        // Set up method

        // Stubbing methods
        when(regionService.searchByName(any(PageRequest.class), anyString())).thenReturn(regions);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/search/paged")
                            .param("name", name)
                            .param("page", String.valueOf(page))
                            .param("size", String.valueOf(size)))
                    .andExpect(status().isOk());
        });

        // Behavior Verifications
        verify(regionService).searchByName(any(PageRequest.class), anyString());

        // Assertions
    }

    @Test
    void searchByNamePaged_ShouldHaveTheDefault_PageOf1_AndSizeOf10_WhenNotProvided() throws Exception {
        // Pre defined values

        // Expected Value
        String name = "3";
        int page = 1;
        int size = 10;

        // Mock data
        Page<Region> regions = new Page<>(new ArrayList<>(), new PageRequest(page, size), 10);

        // Set up method

        // Stubbing methods
        when(regionService.searchByName(any(PageRequest.class), anyString())).thenReturn(regions);

        // Calling the method
        mockMvc.perform(get("/regions/search/paged")
                        .param("name", name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.request.page").value(page))
                .andExpect(jsonPath("$.request.size").value(size));

        // Behavior Verifications
        verify(regionService).searchByName(any(PageRequest.class), anyString());

        // Assertions
    }
}