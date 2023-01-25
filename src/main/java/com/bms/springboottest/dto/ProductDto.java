package com.bms.springboottest.dto;

public record ProductDto(
        String id,
        String name,
        Double price,
        String categoryId
) {
}
