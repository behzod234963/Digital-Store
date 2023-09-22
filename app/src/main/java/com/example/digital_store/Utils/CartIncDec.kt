package com.example.digital_store.Utils

import com.example.digital_store.Models.RoomData

interface CartIncDec {

    fun increaseProduct(count:Int, price:Double,):Double{

        var result=0.0
        result=count*price
        return result

    }

    fun decreaseProduct(count:Int,price:Double):Double{

        var result=0.0
        result-=price
        return result

    }

}