package com.example.digital_store.DataBase.SharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.digital_store.Adapter.CartAdapter

class SharedPreferences(ctx: Context) {

    val sharedPrefences:SharedPreferences=ctx.getSharedPreferences("pref",AppCompatActivity.MODE_PRIVATE)

    fun savePrice(price:Double){

        val editor=sharedPrefences.edit()
        editor.putFloat("price", price.toFloat())
        editor.apply()

    }

    fun getPrice():Double{

        val price=sharedPrefences.getFloat("price",0.0.toFloat())
        return price.toDouble()

    }

    fun clearAll(price:Double){

        val editor = sharedPrefences.edit()
        editor.putFloat("price",0.0.toFloat())
        editor.apply()

    }

}