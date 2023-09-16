package com.example.digital_store.DataBase.SQLite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digital_store.Models.WishListObject


@Dao
interface WishListInterface {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProduct(product:WishListObject)


    @Query("SELECT *FROM data")
    fun getAll():List<WishListObject>

    @Query("SELECT *FROM data WHERE :id=id")
    fun getById(id:Int):WishListObject

    @Delete
    fun clearAll(product:WishListObject)

}