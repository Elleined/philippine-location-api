package com.elleined.philippine_location_api.paging;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PageableTest {

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
        Pageable<Object> pagination = new Pageable<>(list, request, totalElements);

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertThat(totalElements).isEqualTo(pagination.totalElements());
        assertThat(list).isEqualTo(pagination.content());
        assertThat(request).isEqualTo(pagination.request());
    }

    @Test
    void getTotalPages_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Pageable<Object> pagination = new Pageable<>(new ArrayList<>(), request, 10);

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertThat(pagination.getHasNext()).isFalse();
        assertThat(pagination.getHasPrevious()).isFalse();
        assertThat(pagination.getTotalPages()).isEqualTo(1);
    }

    @Test
    void getHasNext_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Pageable<Object> objects = new Pageable<>(new ArrayList<>(), request, 10);

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertThat(objects.getHasNext()).isFalse();
    }

    @Test
    void getHasPrevious_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data
        int page = 1;
        int size = 10;
        PageRequest request = new PageRequest(page, size);
        Pageable<Object> objects = new Pageable<>(new ArrayList<>(), request, 0);

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertThat(objects.getHasPrevious()).isFalse();
    }
}