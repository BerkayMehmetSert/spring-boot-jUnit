package com.bms.springboottest.request

data class CreateProductRequest(
    val name: String,
    val price: Double,
    val categoryId: String
)