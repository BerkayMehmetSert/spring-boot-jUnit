package com.bms.springboottest.dto;

import java.util.Set;

public record CategoryDto(
        String id,
        String name,
        Set<CategoryProductDto> products
) {
}
