package com.bms.springboottest.service;

import com.bms.springboottest.dto.ProductDto;
import com.bms.springboottest.dto.converter.ProductDtoConverter;
import com.bms.springboottest.exception.ProductNotFoundException;
import com.bms.springboottest.model.Product;
import com.bms.springboottest.repository.ProductRepository;
import com.bms.springboottest.request.CreateProductRequest;
import com.bms.springboottest.utilities.BusinessMessage;
import com.bms.springboottest.utilities.LogMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductDtoConverter converter;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository,
                          CategoryService categoryService,
                          ProductDtoConverter converter) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.converter = converter;
    }

    public void createProduct(final CreateProductRequest request) {
        checkIfProductExists(request.getName());

        Product product = new Product(
                request.getName(),
                request.getPrice(),
                categoryService.getCategoryById(request.getCategoryId())
        );

        productRepository.save(product);
        LOGGER.info(LogMessage.Product.PRODUCT_CREATED + product.getId());
    }

    public void updateProductName(final String id, final String name) {
        Product product = getProductById(id);

        if (!product.getName().equals(name)) {
            checkIfProductExists(name);
        }

        Product updatedProduct = new Product(
                product.getId(),
                name,
                product.getPrice(),
                product.getCategory()
        );

        productRepository.save(updatedProduct);
        LOGGER.info(LogMessage.Product.PRODUCT_UPDATED + id);
    }

    public void updateProductPrice(final String id, final double price) {
        Product product = getProductById(id);

        Product updatedProduct = new Product(
                product.getId(),
                product.getName(),
                price,
                product.getCategory()
        );

        productRepository.save(updatedProduct);
        LOGGER.info(LogMessage.Product.PRODUCT_UPDATED + id);
    }

    public void updateProductCategory(final String id, final String categoryId) {
        Product product = getProductById(id);

        Product updatedProduct = new Product(
                product.getId(),
                product.getName(),
                product.getPrice(),
                categoryService.getCategoryById(categoryId)
        );

        productRepository.save(updatedProduct);
        LOGGER.info(LogMessage.Product.PRODUCT_UPDATED +id);
    }

    public void deleteProduct(final String id) {
        productRepository.delete(getProductById(id));
        LOGGER.info(LogMessage.Product.PRODUCT_DELETED + id);
    }

    public ProductDto findProductById(final String id) {
        Product product = getProductById(id);

        LOGGER.info(LogMessage.Product.PRODUCT_FOUND + id);
        return converter.convert(product);
    }

    public ProductDto findProductByName(final String name) {
        Product product = productRepository.findByName(name).orElseThrow(() -> {
            LOGGER.error(LogMessage.Product.PRODUCT_NOT_FOUND_BY_NAME + name);
            return new ProductNotFoundException(BusinessMessage.Product.PRODUCT_NOT_FOUND_BY_NAME + name);
        });

        LOGGER.info(LogMessage.Product.PRODUCT_FOUND_BY_NAME + name);
        return converter.convert(product);
    }

    public ProductDto findProductByCategory(String categoryId) {
        Product product = productRepository.findByCategory_Id(categoryId).orElseThrow(() -> {
            LOGGER.error(LogMessage.Product.PRODUCT_NOT_FOUND_BY_CATEGORY + categoryId);
            return new ProductNotFoundException(BusinessMessage.Product.PRODUCT_NOT_FOUND_BY_CATEGORY + categoryId);
        });

        LOGGER.info(LogMessage.Product.PRODUCT_FOUND_BY_CATEGORY + categoryId);
        return converter.convert(product);
    }

    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            LOGGER.error(LogMessage.Product.PRODUCT_LIST_EMPTY);
            throw new ProductNotFoundException(BusinessMessage.Product.PRODUCT_LIST_EMPTY);
        }

        LOGGER.info(LogMessage.Product.PRODUCT_LISTED + products.size());
        return converter.convert(products);
    }

    private Product getProductById(final String id) {
        return productRepository.findById(id).orElseThrow(() -> {
            LOGGER.error(LogMessage.Product.PRODUCT_NOT_FOUND + id);
            return new ProductNotFoundException(BusinessMessage.Product.PRODUCT_NOT_FOUND + id);
        });
    }

    private void checkIfProductExists(final String name) {
        if (productRepository.existsByName(name)) {
            LOGGER.error(LogMessage.Product.PRODUCT_ALREADY_EXISTS + name);
            throw new ProductNotFoundException(BusinessMessage.Product.PRODUCT_ALREADY_EXISTS + name);
        }
    }
}
