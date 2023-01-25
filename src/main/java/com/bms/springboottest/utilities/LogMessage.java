package com.bms.springboottest.utilities;

public class LogMessage {

    private LogMessage() {
        throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE);
    }

    public static class Category {
        private Category() {
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE);
        }

        public static final String CATEGORY_NOT_FOUND = "Category not found with id: ";
        public static final String CATEGORY_ALREADY_EXISTS = "Category already exists with name: ";
        public static final String CATEGORY_LIST_EMPTY = "Category list is empty.";
        public static final String CATEGORY_CREATED = "Category created successfully. Id: ";
        public static final String CATEGORY_UPDATED = "Category updated successfully. Id: ";
        public static final String CATEGORY_DELETED = "Category deleted successfully. Id: ";
        public static final String CATEGORY_FOUND = "Category found with id: ";
        public static final String CATEGORY_FOUND_BY_NAME = "Category found with name: ";
        public static final String CATEGORY_NOT_FOUND_BY_NAME = "Category not found with name: ";
        public static final String CATEGORY_LISTED = "Category listed successfully. Size: ";
    }

    public static class Product {
        private Product() {
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE);
        }

        public static final String PRODUCT_NOT_FOUND = "Product not found with id: ";
        public static final String PRODUCT_ALREADY_EXISTS = "Product already exists with name: ";
        public static final String PRODUCT_LIST_EMPTY = "Product list is empty.";
        public static final String PRODUCT_CREATED = "Product created successfully. Id: ";
        public static final String PRODUCT_UPDATED = "Product updated successfully. Id: ";
        public static final String PRODUCT_DELETED = "Product deleted successfully. Id: ";
        public static final String PRODUCT_FOUND = "Product found with id: ";
        public static final String PRODUCT_FOUND_BY_NAME = "Product found with name: ";
        public static final String PRODUCT_FOUND_BY_CATEGORY = "Product found with category: ";
        public static final String PRODUCT_NOT_FOUND_BY_NAME = "Product not found with name: ";
        public static final String PRODUCT_LISTED = "Product listed successfully. Size: ";
        public static final String PRODUCT_NOT_FOUND_BY_CATEGORY =  "Product not found with category: ";
    }
}
