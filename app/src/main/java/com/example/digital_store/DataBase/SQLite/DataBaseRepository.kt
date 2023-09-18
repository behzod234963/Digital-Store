package com.example.digital_store.DataBase.SQLite

import android.app.Application
import com.example.digital_store.Models.RoomData

class DataBaseRepository(val app:Application) {

    private val dao=DataBaseCore.appDataBase(app).getDao()


//    Wishlist Repository
    fun saveProduct(product: RoomData.WishList){

        dao.saveProduct(product)

    }

    fun getAll():List<RoomData.WishList>{

        return dao.getAll()

    }

    fun getById(id:Int): RoomData.WishList {

        return dao.getById(id)

    }

    fun clearAll(product: ArrayList<RoomData.WishList>){

        dao.clearAllWish(product)

    }

    fun deleteById(id:Int){

        dao.deleteById(id)

    }

    
    
//    Cart Repository
    fun saveCart(cart:RoomData.Cart){

        dao.saveCart(cart)

    }
    fun getAllCart():List<RoomData.Cart>{

        return dao.getAllCart()

    }

    fun getCartByID(id: Int):RoomData.Cart{

        return dao.getCartByID(id)

    }

    fun clearAllCart(cart:ArrayList<RoomData.Cart>){

        dao.clearAllCart(cart)

    }

    fun deleteCartByID(id: Int){

        dao.deleteCartsByID(id)

    }

}