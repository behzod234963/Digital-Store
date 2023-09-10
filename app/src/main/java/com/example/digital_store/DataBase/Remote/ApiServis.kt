package com.example.digital_store.DataBase.Remote
import com.example.digital_store.Models.CartsItem
import com.example.digital_store.Models.ProductsDetails
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.Models.UsersItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("products/category/{category}")
    fun getCategoryByName(@Path("category")category: String):retrofit2.Call<ArrayList<ProductsItem>>

    @POST("products")
    fun createProduct(@Body()productItem:ProductsDetails):retrofit2.Call<ProductsItem>

    @PUT("products/{id}")
    fun editProduct(@Path("id")id:Int,@Body()productItem:ProductsDetails):retrofit2.Call<ProductsItem>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id")id:Int)

    @GET("carts")
    fun getAllCarts():Call<ArrayList<CartsItem>>

    @GET("carts/{id}")
    fun getCartById(@Path("id")id: Int):Call<ArrayList<CartsItem>>

    @GET("carts/sort")
    fun sortCarts(@Query("sort")sort:String="desc"):retrofit2.Call<ArrayList<CartsItem>>

    @GET("carts/user/{id}")
    fun getUserCarts(@Path("id")id:Int):Call<ArrayList<CartsItem>>

    @POST("carts")
    fun addNewCart(@Body()cart:CartsItem):Call<CartsItem>

    @PUT("carts/{id}")
    fun editCart(@Path("id")id: Int,@Body()cart: CartsItem):Call<CartsItem>

    @DELETE("carts/{id}")
    fun deleteCart(@Path("id")id:Int)

    @GET("users")
    fun getAllUsers():Call<ArrayList<UsersItem>>

    @GET("users/{id}")
    fun getUserById(@Path("id")id: Int):Call<ArrayList<UsersItem>>

    @GET("users/sort")
    fun sortUsers(@Query("sort")sort:String="desc"):retrofit2.Call<ArrayList<UsersItem>>

    @POST("users")
    fun addUser(@Body()user:UsersItem):Call<UsersItem>

    @PUT("users/id")
    fun editUser(@Path("id")id:Int,@Body()user:UsersItem):Call<UsersItem>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id")id: Int)
}