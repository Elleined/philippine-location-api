package com.elleined.philippine_location_api.province;

import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProvinceControllerIntegrationTest {

    @Container
    @ServiceConnection
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.39")
            .withReuse(true);
    @Container
    @ServiceConnection
    private static final RedisContainer redisContainer = new RedisContainer("redis:8.0.2-alpine")
            .withReuse(true);
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll_HappyPath() {
        int regionId = 5; // Region 3

        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces", regionId))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", isA(List.class)))
                    .andExpect(jsonPath("$", not(empty())))
                    .andExpect(jsonPath("$[*].name", hasItem("Nueva Ecija")));
        });
    }

    @Test
    void getAllPaged_HappyPath() {
        int regionId = 5; // Region 3
        int page = 1;
        int size = 10;

        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/paged", regionId)
                            .param("page", String.valueOf(page))
                            .param("size", String.valueOf(size)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content", isA(List.class)))
                    .andExpect(jsonPath("$.content", not(empty())))
                    .andExpect(jsonPath("$.content[*].name", hasItem("Nueva Ecija")))
                    .andExpect(jsonPath("$.content.length()", lessThanOrEqualTo(size)))
                    .andExpect(jsonPath("$.request.page", is(page)))
                    .andExpect(jsonPath("$.request.size", is(size)))
                    .andExpect(jsonPath("$.totalElements", isA(Number.class)));
        });
    }

    @Test
    void searchByName_HappyPath() {
        int regionId = 5; // Region 3
        String name = "Nue";

        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/search", regionId)
                            .param("name", name))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", isA(List.class)))
                    .andExpect(jsonPath("$", not(empty())))
                    .andExpect(jsonPath("$[*].name", hasItem("Nueva Ecija")));
        });

    }

    @Test
    void searchByNamePaged_HappyPath() {
        int regionId = 5; // Region 3
        int page = 1;
        int size = 10;
        String name = "Nue";

        assertDoesNotThrow(() -> {
            mockMvc.perform(get("/regions/{regionId}/provinces/search/paged", regionId)
                            .param("name", name)
                            .param("page", String.valueOf(page))
                            .param("size", String.valueOf(size)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.content", isA(List.class)))
                    .andExpect(jsonPath("$.content", not(empty())))
                    .andExpect(jsonPath("$.content[*].name", hasItem("Nueva Ecija")))
                    .andExpect(jsonPath("$.content.length()", lessThanOrEqualTo(size)))
                    .andExpect(jsonPath("$.request.page", is(page)))
                    .andExpect(jsonPath("$.request.size", is(size)))
                    .andExpect(jsonPath("$.totalElements", isA(Number.class)));
        });
    }
}