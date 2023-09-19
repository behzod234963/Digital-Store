package com.example.digital_store.DataBase.SQLite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digital_store.Models.RoomData


@Dao
interface DataBaseDaO {

//    Wishlist DAO
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProduct(product: RoomData.WishList)

    @Query("SELECT *FROM data")
    fun getAll():List<RoomData.WishList>

    @Query("SELECT *FROM data WHERE :id=id")
    fun getById(id:Int): RoomData.WishList

    @Delete
    fun clearAllWish(product: ArrayList<RoomData.WishList>)

    @Query("DELETE FROM data WHERE :id=id")
    fun deleteById(id: Int)


//    Cart DAO
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCart(cart: RoomData.Cart)

    @Query("SELECT *FROM `carts list`")
    fun getAllCart():List<RoomData.Cart>

    @Query("SELECT *FROM `carts list` WHERE :id=id")
    fun getCartByID(id:Int): RoomData.Cart

    @Delete
    fun clearAllCart(cart:ArrayList<RoomData.Cart>)

    @Query("DELETE FROM `carts list` WHERE :id=id")
    fun deleteCartsByID(id:Int)

}