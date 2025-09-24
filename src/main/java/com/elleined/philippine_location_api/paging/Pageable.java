package com.elleined.philippine_location_api.paging;

import java.util.Collection;

public record Pageable<T>(
        Collection<T> content,
        PageRequest request,
        int totalElements
) {
    public int getTotalPages() {
        return (totalElements + request.size() - 1) / request.size();
    }

    public boolean getHasNext() {
        return request.getOffset() + request.size() < totalElements;
    }

    public boolean getHasPrevious() {
        return request.page() > 1;
    }

    public static <T> Pageable<T> of(Collection<T> content, PageRequest request, int totalElements) {
        return new Pageable<>(content, request, totalElements);
    }
}
