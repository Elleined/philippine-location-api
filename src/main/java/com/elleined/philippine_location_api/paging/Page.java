package com.elleined.philippine_location_api.paging;

import java.io.Serializable;
import java.util.List;

public record Page<T>(
        List<T> content,
        PageRequest request,
        int totalElements
) implements Serializable {
    public int getTotalPages() {
        return (totalElements + request.size() - 1) / request.size();
    }

    public boolean getHasNext() {
        return request.getOffset() + request.size() < totalElements;
    }

    public boolean getHasPrevious() {
        return request.page() > 1;
    }
}
