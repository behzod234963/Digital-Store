package com.example.digital_store.Models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("data")
data class WishListObject(

    @PrimaryKey(autoGenerate = true)
    val id :Int?=null,
    val image:String,
    val title:String,
    val price:Double,
    val rating:String,
    val description:String)