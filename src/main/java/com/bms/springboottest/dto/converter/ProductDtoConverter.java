package com.bms.springboottest.dto.converter;

import com.bms.springboottest.dto.ProductDto;
import com.bms.springboottest.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ProductDtoConverter {
    public ProductDto convert(Product from) {
        return new ProductDto(
                from.getId(),
                from.getName(),
                from.getPrice(),
                Objects.requireNonNull(from.getCategory()).getId()
        );
    }

    public List<ProductDto> convert(List<Product> from) {
        return from.stream().map(this::convert).toList();
    }
}
