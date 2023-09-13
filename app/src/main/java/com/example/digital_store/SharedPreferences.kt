package com.example.digital_store

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

class SharedPreferences(val ctx:Context) {

    val pref: SharedPreferences? =ctx.getSharedPreferences("pref",AppCompatActivity.MODE_PRIVATE)

    fun saveId(id:Int){

        val editor=pref?.edit()
        editor?.putInt("id",id)
        editor?.apply()

    }

    fun getId():Int{

        val id =pref?.getInt("id",1)
        return id!!

    }

}
