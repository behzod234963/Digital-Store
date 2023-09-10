package com.example.digital_store.Models

import com.google.gson.annotations.SerializedName

data class Carts(

	@field:SerializedName("Carts")
	val carts: List<CartsItem?>? = null
)

data class CartsItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("products")
	val products: List<ProductsDetails?>? = null
)

data class ProductsDetails(

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("productId")
	val productId: Int? = null
)
