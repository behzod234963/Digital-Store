package com.example.digital_store.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

class RoomData {


    @Entity("carts")
    data class Cart(

        @PrimaryKey
        val id:Int?=null,
        val image:String,
        val title:String,
        val price:String,

        )

    @Entity("data")
    data class WishList(

        @PrimaryKey(autoGenerate = true)
        val id:Int?=null,
        val image:String?=null,
        val title:String,
        val price: String,
        val rating:String,
        val description:String)


}