package com.elleined.philippine_location_api.paging;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PageTest {

    @Test
    void page_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        List<Object> list = new ArrayList<>();
        int totalElements = list.size();
        Page<Object> pagination = new Page<>(list, request, totalElements);

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertEquals(totalElements, pagination.totalElements());
        assertEquals(list, pagination.content());
        assertEquals(request, pagination.request());
    }

    @Test
    void getTotalPages_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Page<Object> pagination = new Page<>(new ArrayList<>(), request, 10);

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertFalse(pagination.getHasNext());
        assertFalse(pagination.getHasPrevious());

        assertEquals(1, pagination.getTotalPages());
    }

    @Test
    void getHasNext_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Page<Object> objects = new Page<>(new ArrayList<>(), request, 10);

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertFalse(objects.getHasNext());
    }

    @Test
    void getHasPrevious_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Page<Object> objects = new Page<>(new ArrayList<>(), request, 0);

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertFalse(objects.getHasPrevious());
    }
}