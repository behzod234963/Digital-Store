package com.example.digital_store.DataBase.Remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object ApiClient {


    val retrofit=Retrofit.Builder().baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiServis: ApiServis = retrofit.create(ApiServis::class.java)

}