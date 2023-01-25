package com.bms.springboottest.service;

import com.bms.springboottest.dto.CategoryDto;
import com.bms.springboottest.dto.converter.CategoryDtoConverter;
import com.bms.springboottest.exception.CategoryNotFoundException;
import com.bms.springboottest.model.Category;
import com.bms.springboottest.repository.CategoryRepository;
import com.bms.springboottest.utilities.BusinessMessage;
import com.bms.springboottest.utilities.LogMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter converter;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryDtoConverter converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    public void createCategory(final String name) {
        checkIfCategoryExists(name);

        Category category = new Category(name);

        categoryRepository.save(category);
        LOGGER.info(LogMessage.Category.CATEGORY_CREATED + category.getId());
    }

    public void updateCategory(final String id, final String name) {
        Category category = getCategoryById(id);

        if (!category.getName().equals(name)) {
            checkIfCategoryExists(name);
        }

        Category updatedCategory = new Category(
                category.getId(),
                name,
                category.getProducts()
        );

        categoryRepository.save(updatedCategory);
        LOGGER.info(LogMessage.Category.CATEGORY_UPDATED + id);
    }

    public void deleteCategory(final String id) {
        categoryRepository.delete(getCategoryById(id));
        LOGGER.info(LogMessage.Category.CATEGORY_DELETED + id);
    }

    public CategoryDto findCategoryById(final String id) {
        Category category = getCategoryById(id);

        LOGGER.info(LogMessage.Category.CATEGORY_FOUND + id);
        return converter.convert(category);
    }

    public CategoryDto findCategoryByName(final String name) {
        Category category = categoryRepository.findByName(name).orElseThrow(() -> {
            LOGGER.error(LogMessage.Category.CATEGORY_NOT_FOUND_BY_NAME + name);
            throw new CategoryNotFoundException(BusinessMessage.Category.CATEGORY_NOT_FOUND_BY_NAME + name);
        });

        LOGGER.info(LogMessage.Category.CATEGORY_FOUND_BY_NAME + name);
        return converter.convert(category);
    }

    public List<CategoryDto> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            LOGGER.error(LogMessage.Category.CATEGORY_LIST_EMPTY);
            throw new CategoryNotFoundException(BusinessMessage.Category.CATEGORY_LIST_EMPTY);
        }

        LOGGER.info(LogMessage.Category.CATEGORY_LISTED + categories.size());
        return converter.convert(categories);
    }

    protected Category getCategoryById(final String id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            LOGGER.error(LogMessage.Category.CATEGORY_NOT_FOUND + id);
            throw new CategoryNotFoundException(BusinessMessage.Category.CATEGORY_NOT_FOUND + id);
        });
    }

    private void checkIfCategoryExists(final String name) {
        if (categoryRepository.existsByName(name)) {
            LOGGER.error(LogMessage.Category.CATEGORY_ALREADY_EXISTS + name);
            throw new CategoryNotFoundException(BusinessMessage.Category.CATEGORY_ALREADY_EXISTS + name);
        }
    }
}
