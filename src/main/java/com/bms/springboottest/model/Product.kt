package com.bms.springboottest.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
data class Product @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val name: String,

    val price: Double,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "category_id")
    val category: Category?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false
        if (name != other.name) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }

    override fun toString(): String {
        return "Product(id=$id, name='$name', price=$price, category=$category)"
    }
}
