package com.bms.springboottest.dto.converter;

import com.bms.springboottest.dto.CategoryDto;
import com.bms.springboottest.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CategoryDtoConverter {
    private final CategoryProductDtoConverter productDtoConverter;

    public CategoryDtoConverter(CategoryProductDtoConverter productDtoConverter) {
        this.productDtoConverter = productDtoConverter;
    }

    public CategoryDto convert(Category from) {
        return new CategoryDto(
                from.getId(),
                from.getName(),
                productDtoConverter.convert(Objects.requireNonNull(from.getProducts()))
        );
    }

    public List<CategoryDto> convert(List<Category> from) {
        return from.stream().map(this::convert).toList();
    }
}
