package com.bms.springboottest.service;

import com.bms.springboottest.dto.CategoryDto;
import com.bms.springboottest.dto.converter.CategoryDtoConverter;
import com.bms.springboottest.exception.CategoryNotFoundException;
import com.bms.springboottest.model.Category;
import com.bms.springboottest.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryServiceTest {
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;
    private CategoryDtoConverter converter;

    @BeforeEach
    void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        converter = Mockito.mock(CategoryDtoConverter.class);
        categoryService = new CategoryService(categoryRepository, converter);
    }

    @Test
    void testFindCategoryById_WhenCategoryIdExists_ThenReturnCategoryDto() {
        Category category = createCategory();
        CategoryDto categoryDto = createCategoryDto();

        Mockito.when(categoryRepository.findById("id")).thenReturn(Optional.of(category));
        Mockito.when(converter.convert(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.findCategoryById("id");

        assertEquals(result, categoryDto);
    }

    @Test
    void testFindCategoryById_WhenCategoryIdDoesNotExist_ThenThrowCategoryNotFoundException() {
        Mockito.when(categoryRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.findCategoryById("id"));

        Mockito.verifyNoInteractions(converter);
    }

    @Test
    void testFindCategoryByName_WhenCategoryNameExists_ThenReturnCategoryDto() {
        Category category = createCategory();
        CategoryDto categoryDto = createCategoryDto();

        Mockito.when(categoryRepository.findByName("name")).thenReturn(Optional.of(category));
        Mockito.when(converter.convert(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.findCategoryByName("name");

        assertEquals(result, categoryDto);
    }

    @Test
    void testFindCategoryByName_WhenCategoryNameDoesNotExist_ThenThrowCategoryNotFoundException() {
        Mockito.when(categoryRepository.findByName("name")).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.findCategoryByName("name"));

        Mockito.verifyNoInteractions(converter);
    }

    @Test
    void testFindAllCategories_WhenCategoriesExists_ThenCategoryDtoList(){
        List<Category> categories = createCategories();
        List<CategoryDto> categoryDtoList = createCategoryDtoList();

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        Mockito.when(converter.convert(categories)).thenReturn(categoryDtoList);

        List<CategoryDto> result = categoryService.findAllCategories();

        assertEquals(result, categoryDtoList);
    }

    @Test
    void testFindAllCategories_WhenCategoriesDoesNotExist_ThenThrowCategoryNotFoundException(){
        List<Category> categories = List.of();

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        assertThrows(CategoryNotFoundException.class, ()-> categoryService.findAllCategories());

        Mockito.verifyNoInteractions(converter);
    }

    private Category createCategory() {
        return new Category("id", "name", new HashSet<>());
    }

    private List<Category> createCategories() {
        return List.of(createCategory());
    }

    private CategoryDto createCategoryDto() {
        return new CategoryDto("id", "name", new HashSet<>());
    }

    private List<CategoryDto> createCategoryDtoList() {
        return List.of(createCategoryDto());
    }
}