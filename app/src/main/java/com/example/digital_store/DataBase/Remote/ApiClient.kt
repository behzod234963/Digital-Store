package com.example.digital_store.DataBase.Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object ApiClient {

    private const val api_tester=false
    private const val SERVER_PROCESS="https://fakestoreapi.com/"
    private const val SERVER_PRODUCT="https://fakestoreapi.com/"

    val retrofit=Retrofit.Builder().baseUrl(baseUrl()).addConverterFactory(GsonConverterFactory.create()).build()

    private fun baseUrl(): String {

        return if (api_tester){

            SERVER_PROCESS

        }else{

            SERVER_PRODUCT

        }

    }

    val api_servis= retrofit.create(ApiServis::class.java)

}