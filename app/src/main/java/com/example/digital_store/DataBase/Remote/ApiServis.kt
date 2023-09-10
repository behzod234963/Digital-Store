package com.example.digital_store.DataBase.Remote
import android.telecom.Call
import com.example.digital_store.Models.ProductsItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServis {

    @GET("products")
    fun getAllProducts():retrofit2.Call<ArrayList<ProductsItem>>

    @GET("products/{id}")
    fun getProductById(@Path("id")id:Int):retrofit2.Call<ArrayList<ProductsItem>>

    @GET("products/sort")
    fun sortProducts(@Query("sort")sort:String="desc"):retrofit2.Call<ArrayList<ProductsItem>>

    @GET("products/categories")
    fun getAllCategories():retrofit2.Call<ArrayList<ProductsItem>>

}