package com.elleined.philippine_location_api.baranggay;

import com.elleined.philippine_location_api.paging.PageRequest;
import com.elleined.philippine_location_api.paging.Pageable;
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

@WebMvcTest(BaranggayController.class)
class BaranggayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BaranggayService baranggayService;

    @Test
    void searchByNamePaged_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;
        String name = "name";
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Pageable<Baranggay> expected = new Pageable<>(new ArrayList<>(), request, 10);

        // Set up method

        // Stubbing methods
        when(baranggayService.searchByName(anyInt(), anyInt(), anyInt(), anyString(), any(PageRequest.class))).thenReturn(expected);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/{provinceId}/cities/{cityId}/baranggays/search/paged", regionId, provinceId, cityId)
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
        verify(baranggayService).searchByName(anyInt(), anyInt(), anyInt(), anyString(), any(PageRequest.class));

        // Assertions
    }

    @Test
    void searchByNamePaged_ShouldHaveTheDefault_PageOf1_AndSizeOf10_WhenNotProvided() {
        // Pre defined values

        // Expected Value

        // Mock data
        int regionId = 1;
        int provinceId = 1;
        int cityId = 1;
        String name = "name";
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Pageable<Baranggay> expected = new Pageable<>(new ArrayList<>(), request, 10);

        // Set up method

        // Stubbing methods
        when(baranggayService.searchByName(anyInt(), anyInt(), anyInt(), anyString(), any(PageRequest.class))).thenReturn(expected);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/{provinceId}/cities/{cityId}/baranggays/search/paged", regionId, provinceId, cityId)
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
        verify(baranggayService).searchByName(anyInt(), anyInt(), anyInt(), anyString(), any(PageRequest.class));

        // Assertions
    }
}