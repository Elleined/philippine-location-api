package com.elleined.philippine_location_api.province;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.elleined.philippine_location_api.paging.Page;
import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.region.RegionController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ProvinceController.class)
class ProvinceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProvinceService provinceService;

    @Test
    void getAll_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        List<Province> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(provinceService.getAll(anyInt())).thenReturn(expected);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces", regionId))
                    .andExpect(status().isOk());
        });

        // Behavior Verifications
        verify(provinceService).getAll(anyInt());

        // Assertions
    }

    @Test
    void getAllPaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method
        int regionId = 1;
        int page = 1;
        int size = 10;
        PageRequest request = PageRequest.of(page, size);
        Page<Province> expected = new Page<>(new ArrayList<>(), request, 10);

        // Stubbing methods
        when(provinceService.getAll(anyInt(), any(PageRequest.class))).thenReturn(expected);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/paged", regionId)
                            .param("page", String.valueOf(page))
                            .param("size", String.valueOf(size)))
                    .andExpect(status().isOk());
        });

        // Behavior Verifications
        verify(provinceService).getAll(anyInt(), any(PageRequest.class));

        // Assertions
    }

    @Test
    void getAllPaged_ShouldHaveTheDefault_PageOf1_AndSizeOf10_WhenNotProvided() {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method
        int regionId = 1;
        int page = 1;
        int size = 10;
        PageRequest request = PageRequest.of(page, size);
        Page<Province> expected = new Page<>(new ArrayList<>(), request, 10);

        // Stubbing methods
        when(provinceService.getAll(anyInt(), any(PageRequest.class))).thenReturn(expected);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/paged", regionId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.request.page").value(page))
                    .andExpect(jsonPath("$.request.size").value(size));
        });

        // Behavior Verifications
        verify(provinceService).getAll(anyInt(), any(PageRequest.class));

        // Assertions
    }

    @Test
    void searchByName_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        String name = "name";
        List<Province> expected = new ArrayList<>();

        // Set up method

        // Stubbing methods
        when(provinceService.searchByName(anyInt(), anyString())).thenReturn(expected);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/search", regionId)
                            .param("name", name))
                    .andExpect(status().isOk());
        });

        // Behavior Verifications
        verify(provinceService).searchByName(anyInt(), anyString());

        // Assertions
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
        Page<Province> expected = new Page<>(new ArrayList<>(), request, 10);

        // Set up method

        // Stubbing methods
        when(provinceService.searchByName(anyInt(), anyString(), any(PageRequest.class))).thenReturn(expected);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/paged-search", regionId)
                            .param("name", name)
                            .param("page", String.valueOf(page))
                            .param("size", String.valueOf(size)))
                    .andExpect(status().isOk());
        });

        // Behavior Verifications
        verify(provinceService).searchByName(anyInt(), anyString(), any(PageRequest.class));

        // Assertions
    }

    @Test
    void searchByNamePaged_ShouldHaveTheDefault_PageOf1_AndSizeOf10_WhenNotProvided() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        String name = "name";
        int page = 1;
        int size = 10;
        PageRequest request = PageRequest.of(page, size);
        Page<Province> expected = new Page<>(new ArrayList<>(), request, 10);

        // Set up method

        // Stubbing methods
        when(provinceService.searchByName(anyInt(), anyString(), any(PageRequest.class))).thenReturn(expected);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/paged-search", regionId)
                            .param("name", name))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.request.page").value(page))
                    .andExpect(jsonPath("$.request.size").value(size));
        });

        // Behavior Verifications
        verify(provinceService).searchByName(anyInt(), anyString(), any(PageRequest.class));

        // Assertions
    }
}