package com.bms.springboottest.dto.converter;

import com.bms.springboottest.dto.CategoryProductDto;
import com.bms.springboottest.model.Product;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryProductDtoConverter {
    public CategoryProductDto convert(Product from) {
        return new CategoryProductDto(
                from.getId(),
                from.getName(),
                from.getPrice()
        );
    }

    public Set<CategoryProductDto> convert(Set<Product> from) {
        return from.stream().map(this::convert).collect(Collectors.toSet());
    }
}
