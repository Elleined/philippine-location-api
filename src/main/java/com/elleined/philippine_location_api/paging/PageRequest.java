package com.elleined.philippine_location_api.paging;

public record PageRequest(int page, int size) {

    public PageRequest {
        if (page <= 0) {
            throw new IllegalArgumentException("Page number must be greater than zero");
        }

        if (size <= 0) {
            throw new IllegalArgumentException("Page size must be greater than zero");
        }
    }

    public static PageRequest of(int page, int size) {
        return new PageRequest(page, size);
    }

    public int getOffset() {
        return (page - 1) * size;
    }
}
