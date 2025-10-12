package com.elleined.philippine_location_api.paging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public record Pageable<T>(
        @JsonProperty("content") Collection<T> content,
        @JsonProperty("page_request") PageRequest request,
        @JsonProperty("total_elements") int totalElements
) {
    @JsonProperty("total_pages")
    public int getTotalPages() {
        return (totalElements + request.size() - 1) / request.size();
    }

    public static <T> Pageable<T> of(Collection<T> content, PageRequest request, int totalElements) {
        return new Pageable<>(content, request, totalElements);
    }
}
