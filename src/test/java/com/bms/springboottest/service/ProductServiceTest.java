package com.bms.springboottest.service;

import com.bms.springboottest.dto.ProductDto;
import com.bms.springboottest.dto.converter.ProductDtoConverter;
import com.bms.springboottest.exception.ProductNotFoundException;
import com.bms.springboottest.model.Category;
import com.bms.springboottest.model.Product;
import com.bms.springboottest.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private ProductService productService;
    private CategoryService categoryService;
    private ProductRepository productRepository;
    private ProductDtoConverter converter;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        categoryService = Mockito.mock(CategoryService.class);
        converter = Mockito.mock(ProductDtoConverter.class);
        productService = new ProductService(productRepository, categoryService, converter);
    }

    @Test
    void testFindProductById_WhenProductIdExists_ThenReturnProductDto() {
        Product product = createProduct();
        ProductDto productDto = createProductDto();
        Category category = createCategory();

        Mockito.when(categoryService.getCategoryById("id")).thenReturn(category);
        Mockito.when(productRepository.findById("id")).thenReturn(Optional.of(product));
        Mockito.when(converter.convert(product)).thenReturn(productDto);

        ProductDto result = productService.findProductById("id");

        assertEquals(result, productDto);
    }

    @Test
    void testFindProductById_WhenProductIdDoesNotExists_ThenThrowProductNotFoundException() {
        Mockito.when(productRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findProductById("id"));

        Mockito.verifyNoInteractions(converter);
    }

    @Test
    void testFindProductByName_WhenProductNameExists_ThenReturnProductDto() {
        Product product = createProduct();
        ProductDto productDto = createProductDto();
        Category category = createCategory();

        Mockito.when(categoryService.getCategoryById("id")).thenReturn(category);
        Mockito.when(productRepository.findByName("name")).thenReturn(Optional.of(product));
        Mockito.when(converter.convert(product)).thenReturn(productDto);

        ProductDto result = productService.findProductByName("name");

        assertEquals(result, productDto);
    }

    @Test
    void testFindProductByName_WhenProductNameDoesNotExist_ThenThrowProductNotFoundException() {
        Mockito.when(productRepository.findByName("name")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findProductByName("name"));

        Mockito.verifyNoInteractions(converter);
    }

    @Test
    void testFindProductByCategory_WhenProductCategory_CategoryIdExists_ThenReturnProductDto() {
        Product product = createProduct();
        ProductDto productDto = createProductDto();
        Category category = createCategory();

        Mockito.when(categoryService.getCategoryById("id")).thenReturn(category);
        Mockito.when(productRepository.findByCategory_Id("id")).thenReturn(Optional.of(product));
        Mockito.when(converter.convert(product)).thenReturn(productDto);

        ProductDto result = productService.findProductByCategory("id");

        assertEquals(result, productDto);
    }

    @Test
    void testFindProductByCategory_WhenProductCategory_CategoryIdDoesNotExist_ThenThrowProductNotFoundException() {
        Mockito.when(productRepository.findByCategory_Id("id")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.findProductByCategory("id"));

        Mockito.verifyNoInteractions(converter);
    }

    @Test
    void testFindAllProducts_WhenProductsExist_ThenReturnProductDtoList() {
        List<Product> products = createProducts();
        List<ProductDto> productDtoList = createProductDtoList();
        Category category = createCategory();

        Mockito.when(categoryService.getCategoryById("id")).thenReturn(category);
        Mockito.when(productRepository.findAll()).thenReturn(products);
        Mockito.when(converter.convert(products)).thenReturn(productDtoList);

        List<ProductDto> result = productService.findAllProducts();

        assertEquals(result, productDtoList);
    }

    @Test
    void testFindAllProducts_WhenProductsDoNotExist_ThenThrowStudentNotFoundException() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of());

        assertThrows(ProductNotFoundException.class, () -> productService.findAllProducts());

        Mockito.verifyNoInteractions(converter);
    }

    private Product createProduct() {
        return new Product("id", "name", 1.0, createCategory());
    }

    private ProductDto createProductDto() {
        return new ProductDto("id", "name", 1.0, createCategory().getId());
    }

    private List<Product> createProducts() {
        return List.of(createProduct());
    }

    private List<ProductDto> createProductDtoList() {
        return List.of(createProductDto());
    }

    private Category createCategory() {
        return new Category("id", "name", new HashSet<>());
    }
}