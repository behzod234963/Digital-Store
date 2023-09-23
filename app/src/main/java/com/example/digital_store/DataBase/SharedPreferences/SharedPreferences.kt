package com.example.digital_store.DataBase.SharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.digital_store.Adapter.CartAdapter

class SharedPreferences(ctx: Context) {

    val sharedPrefences:SharedPreferences=ctx.getSharedPreferences("pref",AppCompatActivity.MODE_PRIVATE)

    fun savePrice(price:Double){

        val editor=sharedPrefences.edit()
        editor.putInt("price", price.toInt())
        editor.apply()

    }

    fun getPrice():Double{

        val price=sharedPrefences.getInt("price",0)
        return price.toDouble()

    }

    fun clearAll(price:Double){

        val editor = sharedPrefences.edit()
        editor.putInt("price",0)
        editor.apply()

    }

    fun saveStr(str:String){

        var edt=sharedPrefences.edit()
        edt.putString("str",str)
        edt.apply()

    }

    fun getStr():String=sharedPrefences.getString("str","1")!!

    fun saveCount(count:Int){

        val editor =sharedPrefences.edit()
        editor.putInt("count",count)
        editor.apply()

    }

    fun getCount():Int{

        val count=sharedPrefences.getInt("count",1)
        return count

    }

}