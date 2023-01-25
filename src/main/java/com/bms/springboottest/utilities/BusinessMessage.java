package com.bms.springboottest.utilities;

public class BusinessMessage {
    public static final String ILLEGAL_STATE = "Utility class, please contact the administrator";

    private BusinessMessage() {
        throw new IllegalStateException(ILLEGAL_STATE);
    }

    public static class Category {
        private Category() {
            throw new IllegalStateException(ILLEGAL_STATE);
        }

        public static final String CATEGORY_NOT_FOUND = "Category not found with id: ";
        public static final String CATEGORY_NOT_FOUND_BY_NAME = "Category not found with name: ";
        public static final String CATEGORY_ALREADY_EXISTS = "Category already exists with name: ";
        public static final String CATEGORY_LIST_EMPTY = "Category list is empty.";
    }

    public static class Product {
        private Product() {
            throw new IllegalStateException(ILLEGAL_STATE);
        }

        public static final String PRODUCT_NOT_FOUND = "Product not found with id: ";
        public static final String PRODUCT_NOT_FOUND_BY_NAME = "Product not found with name: ";
        public static final String PRODUCT_ALREADY_EXISTS = "Product already exists with name: ";
        public static final String PRODUCT_LIST_EMPTY = "Product list is empty.";
        public static final String PRODUCT_NOT_FOUND_BY_CATEGORY = "Product not found with category: ";
    }
}
